package com.ecommerce.bakerymanagement.biz.client;

import java.util.List;

import com.ecommerce.bakerymanagement.exceptions.BakeryConfigurationException;
import com.ecommerce.bakerymanagement.model.CakeMasterDetails;

public interface IBakeryManagementMasterClient {
	
	
	void saveCakeMasterDetails(CakeMasterDetails cakeMasterDetails) throws BakeryConfigurationException;
	
	
	List<CakeMasterDetails> getCakeDetails() throws BakeryConfigurationException;
	
	List<CakeMasterDetails> getCakeByFlavour(String flavour) throws BakeryConfigurationException;
	
	
	CakeMasterDetails getCakeDetailsViaId(String cakeId) throws BakeryConfigurationException;

}
