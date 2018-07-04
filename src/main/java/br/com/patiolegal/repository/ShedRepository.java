package br.com.patiolegal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.patiolegal.domain.Shed;

public interface ShedRepository extends MongoRepository<Shed, String>{
	
}
