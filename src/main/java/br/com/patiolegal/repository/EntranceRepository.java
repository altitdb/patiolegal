package br.com.patiolegal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.patiolegal.domain.Protocol;

@Repository
public interface EntranceRepository extends MongoRepository<Protocol, String> {

    
}
