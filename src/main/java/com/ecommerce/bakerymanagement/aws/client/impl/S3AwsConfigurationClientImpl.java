package com.ecommerce.bakerymanagement.aws.client.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.ecommerce.bakerymanagement.aws.client.IS3AwsConfigurationClient;
import com.ecommerce.bakerymanagement.configuration.SpringS3AwsConfig;
import com.ecommerce.bakerymanagement.helper.CommonHelper;
import com.ecommerce.bakerymanagement.logic.BakeryHelper;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class S3AwsConfigurationClientImpl implements IS3AwsConfigurationClient {

	@Autowired
	private SpringS3AwsConfig springS3AwsConfig;

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private BakeryHelper bakeryHelper;

	@Autowired
	private CommonHelper commonHelper;
	
	
	
	@Bean
	public static AmazonS3Client amazonS3Client() {
		return (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion("us-east-1")
				.withCredentials(new DefaultAWSCredentialsProviderChain()).build();
	}

	@SuppressWarnings("deprecation")
	@PostConstruct
	private void intializeAwsCredentials() {

		AWSCredentials awsCredentials = new BasicAWSCredentials(springS3AwsConfig.getAccessKey(),
				springS3AwsConfig.getSecretKey());
		this.amazonS3 = new AmazonS3Client(awsCredentials);
	}

	@Override
	public String s3Uplod(MultipartFile multipartFile, Map<String, String> bucketDetails) {

		try {

			StringBuilder awsFileUploadPathBuilder = this.bakeryHelper.getS3FileLocation(bucketDetails);
			File file = bakeryHelper.convertMultiPartToFile(multipartFile);
			this.amazonS3.putObject(new PutObjectRequest(String.valueOf(awsFileUploadPathBuilder), file.getName(), file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			return this.commonHelper.getS3DownloadUrl(bucketDetails);
		} catch (Exception e) {
			log.error("mainException: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void downloadS3File(Map<String, String> bucketDetails) {

		try {
			StringBuilder awsFileUploadPathBuilder = this.bakeryHelper.getS3FileLocation(bucketDetails);
			S3Object s3object = this.amazonS3.getObject(
					new GetObjectRequest(String.valueOf(awsFileUploadPathBuilder), bucketDetails.get("fileName")));

			InputStream is = s3object.getObjectContent();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[4096];
			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, len);
			}

		} catch (IOException ioe) {
			log.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException ase) {

			log.info("sCaught an AmazonServiceException from GET requests, rejected reasons:");
			log.info("Error Message:    " + ase.getMessage());
			log.info("HTTP Status Code: " + ase.getStatusCode());
			log.info("AWS Error Code:   " + ase.getErrorCode());
			log.info("Error Type:       " + ase.getErrorType());
			log.info("Request ID:       " + ase.getRequestId());

			throw ase;
		} catch (AmazonClientException ace) {

			log.info("Caught an AmazonClientException: ");
			log.info("Error Message: " + ace.getMessage());

			throw ace;
		}

	}

}
