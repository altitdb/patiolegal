package br.com.patiolegal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.patiolegal.domain.Protocol;

public interface EntranceRepository extends MongoRepository<Protocol, String> {

    
}
