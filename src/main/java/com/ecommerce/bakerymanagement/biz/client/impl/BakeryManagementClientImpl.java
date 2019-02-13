package com.ecommerce.bakerymanagement.biz.client.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.bakerymanagement.biz.client.IBakeryManagementMasterClient;
import com.ecommerce.bakerymanagement.exceptions.BakeryConfigurationException;
import com.ecommerce.bakerymanagement.helper.CommonHelper;
import com.ecommerce.bakerymanagement.model.CakeMasterDetails;
import com.ecommerce.bakerymanagement.model.DocumentDetails;
import com.ecommerce.bakerymanagement.mongodb.repository.CakeRepository;
import com.ecommerce.bakerymanagement.mongodb.repository.DocumentRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class BakeryManagementClientImpl implements IBakeryManagementMasterClient {

	@Autowired
	private CakeRepository cakeRepositry;

	@Autowired
	private DocumentRepository documentRepositry;

	@Autowired
	private CommonHelper commonHelper;

	@Override
	public void saveCakeMasterDetails(CakeMasterDetails cakeMasterDetails) throws BakeryConfigurationException {

		CakeMasterDetails customCakeMasterDetailsObject = commonHelper.setCakeMasterObject(cakeMasterDetails);
		List<DocumentDetails> customDocumentDetailsList = commonHelper
				.setDocumentDetailsObject(cakeMasterDetails.getDocumentDetails());
		customCakeMasterDetailsObject.setDocumentDetails(customDocumentDetailsList);
		documentRepositry.saveAll(customCakeMasterDetailsObject.getDocumentDetails());
		cakeRepositry.save(customCakeMasterDetailsObject);

	}

	@Override
	public List<CakeMasterDetails> getCakeDetails() throws BakeryConfigurationException {

		return cakeRepositry.findAll();

	}

	@Override
	public List<CakeMasterDetails> getCakeByFlavour(String flavour) throws BakeryConfigurationException {

		return null;
	}

	@Override
	public CakeMasterDetails getCakeDetailsViaId(String cakeId) throws BakeryConfigurationException {

		try {
			cakeRepositry.existsById(cakeId);

			List<CakeMasterDetails> cakeMasterDetails = cakeRepositry.findCakeMasterDetailsByCakeId(cakeId);
		
			return cakeMasterDetails.get(0);
		} catch (Exception e) {
			log.error("getCakeDetailsViaId", e);
		}

		return null;
	}
	
	public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

}
