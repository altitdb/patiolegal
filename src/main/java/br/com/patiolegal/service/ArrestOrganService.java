package br.com.patiolegal.service;

import br.com.patiolegal.dto.ArrestOrganRequestDTO;
import br.com.patiolegal.dto.ArrestOrganResponseDTO;


public interface ArrestOrganService {
	
	ArrestOrganResponseDTO arrestOrgan(ArrestOrganRequestDTO request); 

}
