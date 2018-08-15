package br.com.patiolegal.service;

import java.io.InputStream;

import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.SealRequestDTO;

public interface SealService {
	
    FileIdentifierDTO generate(SealRequestDTO request);

    InputStream get(String id);

}
