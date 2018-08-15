package br.com.patiolegal.service;

import java.io.InputStream;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Configuration;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.BusinessException;
import br.com.patiolegal.exception.ConfigurationNotFoundException;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.repository.ConfigurationRepository;
import br.com.patiolegal.repository.ProtocolRepository;

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
	

	@Override
	public InputStream generate(SealRequestDTO request) {
		Optional<Protocol> protocol = protocolRepository.findByProtocol(request.getProtocol());
		
		if (protocol.isPresent()){
			
			LOG.debug("Validando quantidade ja impressa...");
			validatePrintSealLimit(protocol.get(), request.getAmount());
			
			ProtocolDTO dto = new ProtocolDTOBuilder()
								.withProtocol(protocol.get().getProtocol())
								.withAuthentication(protocol.get().getAuthentication())
								.build();
			return reportUtils.generateSealReport(request, dto);
		}
		LOG.error("Protocolo não encontrado em base de dados: " + request.getProtocol());
		throw new ProtocolNotFoundException();
	}


	private void validatePrintSealLimit(Protocol protocol, Integer amountRequired) {
		Integer amountPrinted = protocol
						 .getSeals()
						 .stream()
						 .mapToInt(seal -> seal.getAmount())
						 .sum();
		
		LOG.debug("Quantidade ja impressa de lacres : " + amountPrinted);

		Optional<Configuration> configuration = configurationRepository.findByKey(KEY_PRINT_SEAL_LIMIT);
		if (configuration.isPresent()) {
			Integer limitPrintConfig = new Integer(configuration.get().getValue());
			LOG.debug("Quantidade máxima permitida : " + limitPrintConfig);
			if (limitPrintConfig < (amountPrinted + amountRequired)) {
				throw new BusinessException(KEY_PRINT_SEAL_LIMIT, "Execdido valor máximo de impressões configurado");
			}
		} else {
			LOG.error("Configuração não encontrada: " + KEY_PRINT_SEAL_LIMIT);
			throw new ConfigurationNotFoundException();
		}
		
	}

}
