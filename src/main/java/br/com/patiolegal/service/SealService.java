package br.com.patiolegal.service;

import java.io.InputStream;

import br.com.patiolegal.dto.SealRequestDTO;

public interface SealService {
	
	InputStream generate(SealRequestDTO request);

}
