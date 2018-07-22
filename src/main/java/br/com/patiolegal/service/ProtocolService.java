package br.com.patiolegal.service;

import java.io.InputStream;

import br.com.patiolegal.dto.ProtocolRequestDTO;

public interface ProtocolService {

	InputStream generate(ProtocolRequestDTO request);
	
}
