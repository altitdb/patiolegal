package br.com.patiolegal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Company;
import br.com.patiolegal.dto.CompanyDTO;
import br.com.patiolegal.dto.CompanyDTO.CompanyBuilder;
import br.com.patiolegal.exception.CompanyNotFoundException;
import br.com.patiolegal.repository.CompanyRepository;

@Service
public class CompanyServiceBean implements CompanyService{

	@Autowired
	private CompanyRepository repository;
	
	@Override
	public CompanyDTO findById(String id) {
		Optional<Company> company = repository.findById(id);
		if(company.isPresent()){
			CompanyDTO dto = new CompanyBuilder()
					.withName(company.get().getName())
					.withSocialName(company.get().getSocialName())
					.withPublicPlace(company.get().getPublicPlace())
					.withPostalCode(company.get().getPostalCode())
					.withCity(company.get().getCity())
					.withState(company.get().getState())
					.withPhone(company.get().getPhone())
					.withImage(company.get().getImage())
					.build();
			return dto;
		}
		throw new CompanyNotFoundException();
	}
	
}
