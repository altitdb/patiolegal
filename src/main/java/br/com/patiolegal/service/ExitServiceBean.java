package br.com.patiolegal.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Exit;
import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.dto.ExitRequestDTO;
import br.com.patiolegal.exception.BusinessException;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.repository.EntranceRepository;

@Service
public class ExitServiceBean implements ExitService {

    private static final Logger LOG = Logger.getLogger(ExitServiceBean.class);

    @Autowired
    private EntranceRepository repository;

    @Override
    public void exit(ExitRequestDTO request) {
        LOG.debug("Dados recebidos na requisição: " + request);

        List<Protocol> protocols = repository.findByProtocol(request.getProtocol());
        if (protocols.isEmpty()) {
            throw new ProtocolNotFoundException();
        }

        LOG.debug("Protocolo encontrado. Iniciando processo de saida...");

        Protocol protocol = protocols.get(0);
        if (protocol.getExit() != null) {
            throw new BusinessException("exit", "Protocolo já baixado.");
        }

        Exit exit = new Exit(request.getDate(), request.getUsername(), request.getTaxId(), request.getName());
        protocol.setExit(exit);

        LOG.debug("Efetuando saída.");
        repository.save(protocol);
        LOG.debug("Saída efetuada.");
    }

}
