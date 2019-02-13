package com.ecommerce.bakerymanagement.mongodb.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.bakerymanagement.model.CakeMasterDetails;

@Repository
public interface CakeRepository extends MongoRepository<CakeMasterDetails, String> {
	

	List<CakeMasterDetails> findCakeMasterDetailsByCreatedDate(Timestamp createdDate);
	
	List<CakeMasterDetails> findByFlavourLike(String flavour);
	
	@Query("{'cakeId':?0}")
	List<CakeMasterDetails> findCakeMasterDetailsByCakeId(String cakeId);

	
}
