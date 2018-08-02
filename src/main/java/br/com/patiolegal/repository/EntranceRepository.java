package br.com.patiolegal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.patiolegal.domain.Protocol;

@Repository
public interface EntranceRepository extends MongoRepository<Protocol, String>, QuerydslPredicateExecutor<Protocol> {

	@Query(value = "{'entrance.vehicle.originalPlate': :#{#originalPlate}, 'exit': null}")
	List<Protocol> findOriginalPlateWithoutExit(@Param("originalPlate") String originalPlate);

	List<Protocol> findByProtocol(String protocol);

}
