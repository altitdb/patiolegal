package br.com.patiolegal.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Configuration;
import br.com.patiolegal.domain.Location;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.domain.Seal;
import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.BusinessException;
import br.com.patiolegal.exception.ConfigurationNotFoundException;
import br.com.patiolegal.exception.NotFoundException;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.repository.ConfigurationRepository;
import br.com.patiolegal.repository.ProtocolRepository;
import br.com.patiolegal.repository.SealRepository;

@Service
public class SealServiceBean implements SealService {

    private static final Logger LOG = LogManager.getLogger(SealServiceBean.class);
    private static final String KEY_PRINT_SEAL_LIMIT = "print.seal.limit";
    @Autowired
    private ReportUtils reportUtils;
    @Autowired
    private ProtocolRepository protocolRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private SealRepository sealRepository;

    @Override
    public FileIdentifierDTO generate(SealRequestDTO request) {
        Optional<Protocol> result = protocolRepository.findByProtocol(request.getProtocol());
        if (!result.isPresent()) {
            LOG.error("Protocolo não encontrado em base de dados: " + request.getProtocol());
            throw new ProtocolNotFoundException();
        }
        
        LOG.debug("Validando quantidade de lacres...");
        validatePrintSealLimit(request.getAmount());

		Protocol protocol = result.get();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Location location = protocol.getEntrance().getLocation();
		String dateProtocol = protocol.getDate().format(formatter);

		LOG.debug("Criando file para o lacre gerado...");
		byte[] file = reportUtils.generateSealReport(request, location.toString(), protocol.getAuthentication(), dateProtocol);

        Seal seal = new Seal();
        seal.setFile(new Binary(BsonBinarySubType.BINARY, file));
        seal.generateAuthentication();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        seal.setUsername(username);
        LOG.debug("Salvando lacres...");
        sealRepository.save(seal);
        
        protocol.addSeal(seal);
        LOG.debug("Salvando protocolo...");
        protocolRepository.save(protocol);
        LOG.debug("Lacre gerado com sucesso.");
        return new FileIdentifierDTO(seal.getId());
    }

    private void validatePrintSealLimit(Integer amount) {
        Optional<Configuration> configuration = configurationRepository.findByKey(KEY_PRINT_SEAL_LIMIT);
        if (configuration.isPresent()) {
            Integer limitPrintConfig = new Integer(configuration.get().getValue());
            LOG.debug("Quantidade máxima permitida : " + limitPrintConfig);
            if (amount > limitPrintConfig) {
                throw new BusinessException(KEY_PRINT_SEAL_LIMIT, "Excedido valor máximo de impressões configurado");
            }
        } else {
            LOG.error("Configuração não encontrada: " + KEY_PRINT_SEAL_LIMIT);
            throw new ConfigurationNotFoundException();
        }

    }

    @Override
    public InputStream get(String id) {
        Optional<Seal> seal = sealRepository.findById(id);
        if (seal.isPresent()) {
            Binary file = seal.get().getFile();
            return new ByteArrayInputStream(file.getData());
        }
        throw new NotFoundException();
    }

}
