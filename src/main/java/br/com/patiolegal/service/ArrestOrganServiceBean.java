package br.com.patiolegal.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.ArrestOrgan;
import br.com.patiolegal.dto.ArrestOrganRequestDTO;
import br.com.patiolegal.dto.ArrestOrganResponseDTO;
import br.com.patiolegal.dto.ArrestOrganResponseDTO.ArrestOrganResponseBuilder;
import br.com.patiolegal.repository.ArrestOrganRepository;

@Service
public class ArrestOrganServiceBean implements ArrestOrganService {
	
	@Autowired
	private ArrestOrganRepository arrestOrganRepository;
	
	@Override
	public ArrestOrganResponseDTO arrestOrgan(ArrestOrganRequestDTO request) {
		String arrestOrgan = StringUtils.upperCase(request.getInitials());
		
		ArrestOrgan initials = arrestOrganRepository.findByInitials(request.getInitials());
		
		ArrestOrganResponseDTO response = new ArrestOrganResponseBuilder().withAccessToken("DSADSA").withProfile("ADMIN")
				.withInitials(arrestOrgan).build();
		return response;
	}

}
