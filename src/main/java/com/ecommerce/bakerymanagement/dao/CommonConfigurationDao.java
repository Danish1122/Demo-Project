package com.ecommerce.bakerymanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ecommerce.bakerymanagement.model.CakeMasterDetails;

@Component
public class CommonConfigurationDao {

	@Autowired
	private MongoOperations mongoOperations;

	public List<CakeMasterDetails> getCakeInfoViaCreatedDate() {

		try {
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "created_date"));
			query.limit(10);
	
			List<CakeMasterDetails> cakeMasterDetailsList = mongoOperations.find(query, CakeMasterDetails.class);
	
			return cakeMasterDetailsList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
