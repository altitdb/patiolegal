package br.com.patiolegal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.patiolegal.domain.ArrestOrgan;

@Repository
public interface ArrestOrganRepository extends MongoRepository<ArrestOrgan, String>{
	
	ArrestOrgan findByInitials(String initials);

}
