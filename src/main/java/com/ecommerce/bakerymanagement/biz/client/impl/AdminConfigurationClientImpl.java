package com.ecommerce.bakerymanagement.biz.client.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.bakerymanagement.biz.client.IAdminConfigurationClient;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class AdminConfigurationClientImpl implements IAdminConfigurationClient {

	/*
	 * @Autowired private IS3AwsConfigurationClient s3AwsConfigurationClient;
	 */

	@Override
	public Map<String, String> saveDocuments(List<MultipartFile> multipartFiles, Map<String, String> bucketDetails) {

		try {
			for (MultipartFile multipartFile : multipartFiles) {
//				s3AwsConfigurationClient.s3Uplod(multipartFile, bucketDetails);

			}
		} catch (Exception e) {
			log.error("");
			log.error("", e);
		}

		return null;
	}

}
