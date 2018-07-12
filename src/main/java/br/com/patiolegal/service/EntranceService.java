package br.com.patiolegal.service;

import java.util.List;

import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolRequestDTO;

public interface EntranceService {

    String save(ProtocolRequestDTO request);

    List<ProtocolDTO> find();

}
