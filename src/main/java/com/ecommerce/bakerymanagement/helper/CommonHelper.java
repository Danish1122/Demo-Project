package com.ecommerce.bakerymanagement.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ecommerce.bakerymanagement.configuration.SpringS3AwsConfig;
import com.ecommerce.bakerymanagement.model.CakeMasterDetails;
import com.ecommerce.bakerymanagement.model.DocumentDetails;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CommonHelper {

	private SpringS3AwsConfig s3AwsConfig;

	public String getS3DownloadUrl(Map<String, String> bucketDetails) {
		StringBuilder urlBuilder = new StringBuilder(s3AwsConfig.getS3Url());
		urlBuilder.append("/");
		urlBuilder.append(bucketDetails.get("bucketName"));
		urlBuilder.append("/");
		urlBuilder.append(bucketDetails.get("folderName"));
		urlBuilder.append("/");
		urlBuilder.append(bucketDetails.get("fileName"));

		return String.valueOf(urlBuilder);
	}

	public CakeMasterDetails setCakeMasterObject(CakeMasterDetails cakeMasterDetails) {

		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		cakeMasterDetails.setCakeId(UUID.randomUUID().toString());
		cakeMasterDetails.setCreatedDate(createdDate);
		cakeMasterDetails.setLastModifiedDate(createdDate);
		return cakeMasterDetails;
	}

	public List<DocumentDetails> setDocumentDetailsObject(List<DocumentDetails> documentDetailsList) {

		List<DocumentDetails> listOfDocuments = new ArrayList<>();
		for (DocumentDetails customDocmentDetailsObject : documentDetailsList) {

			Timestamp createdDate = new Timestamp(System.currentTimeMillis());
			customDocmentDetailsObject.setDocumentId(UUID.randomUUID().toString());
			customDocmentDetailsObject.setCreatedDate(createdDate);
			customDocmentDetailsObject.setLastModifiedDate(createdDate);
			listOfDocuments.add(customDocmentDetailsObject);
		}
		return listOfDocuments;
	}

		
}
