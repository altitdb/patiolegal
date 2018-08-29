package br.com.patiolegal.service;

import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.ProtocolRequestDTO;

public interface ProtocolService {

	FileIdentifierDTO generateProtocol(ProtocolRequestDTO request);
	
}
