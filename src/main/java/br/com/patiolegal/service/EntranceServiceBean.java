package br.com.patiolegal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.repository.EntranceRepository;

@Service
public class EntranceServiceBean implements EntranceService {

    @Autowired
    private EntranceRepository entranceRepository;

    @Override
    public String save(ProtocolRequestDTO request) {
        Protocol protocol = new Protocol();
        entranceRepository.save(protocol);
        return "PROTOCOLO";
    }

    @Override
    public List<ProtocolDTO> find() {
        List<Protocol> protocols = entranceRepository.findAll();

        return protocols.stream().map(protocol -> new ProtocolDTOBuilder().withProtocol(protocol.getProtocol()).build())
                .collect(Collectors.toList());
    }

}
