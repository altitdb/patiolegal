package br.com.patiolegal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Protocol;
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

}
