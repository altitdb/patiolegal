package br.com.patiolegal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.patiolegal.domain.ArrestOrgan;
import br.com.patiolegal.dto.ArrestOrganDTO;
import br.com.patiolegal.dto.ArrestOrganDTO.ArrestOrganBuilder;
import br.com.patiolegal.repository.ArrestOrganRepository;

@Service
public class ArrestOrganServiceBean implements ArrestOrganService {
	
	@Autowired
	private ArrestOrganRepository repository;
	
	@Override
	public List<ArrestOrganDTO> findAll() { 
		
			List<ArrestOrgan> arrestOrgans = repository.findAll();
			
			return arrestOrgans.stream()
									.map(arrestOrgan -> new ArrestOrganBuilder()
															.withInitials(arrestOrgan.getInitials())
															.withDescription(arrestOrgan.getDescription())
															.build())
									.collect(Collectors.toList());
		
		
	}	
}
