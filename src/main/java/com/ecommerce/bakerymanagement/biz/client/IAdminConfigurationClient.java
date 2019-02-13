package com.ecommerce.bakerymanagement.biz.client;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IAdminConfigurationClient {

	Map<String, String> saveDocuments(List<MultipartFile> multipartFiles, Map<String, String> bucketDetails);

}
