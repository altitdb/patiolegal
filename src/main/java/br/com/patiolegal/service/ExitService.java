package br.com.patiolegal.service;

import br.com.patiolegal.dto.ExitRequestDTO;
import br.com.patiolegal.dto.ExitResponseDTO;

public interface ExitService {
	ExitResponseDTO exit(ExitRequestDTO request);
}
