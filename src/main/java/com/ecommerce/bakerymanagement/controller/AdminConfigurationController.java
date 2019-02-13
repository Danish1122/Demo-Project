package com.ecommerce.bakerymanagement.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.bakerymanagement.biz.client.IAdminConfigurationClient;
import com.ecommerce.bakerymanagement.constants.WebConstants;
import com.ecommerce.bakerymanagement.helper.RequestValidatorHelper;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;

@RequestMapping(value = "/admin/v1")
@RestController
@Api(value = "onlinecakestore", description = "Admin Operations pertaining to products in Online Store")
@Log4j
@Component
public class AdminConfigurationController {

	@Autowired
	private IAdminConfigurationClient adminConfigurationClient;

	@Autowired
	private RequestValidatorHelper requestValidatorHelper;

	@PostMapping(value = WebConstants.SAVE_ADMIN_DOCUMENTS)
	private @ResponseBody Object saveNewDocuments(
			@RequestParam(name = "file", required = true) List<MultipartFile> multipartFiles,
			@RequestParam(name = "bucketDetails", required = true) String bucketDetails,
			HttpServletResponse httpServletResponse) {
		try {

			Map<String, String> bucketMapDetails = requestValidatorHelper.convertStringJsonToMapObject(bucketDetails);
			adminConfigurationClient.saveDocuments(multipartFiles, bucketMapDetails);

		} catch (Exception e) {
			log.info("saveNewDocuments", e);
		}
		return null;
	}
	
	
	
	

}
