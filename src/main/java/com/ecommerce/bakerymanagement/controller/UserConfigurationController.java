package com.ecommerce.bakerymanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bakerymanagement.biz.client.IBakeryManagementMasterClient;
import com.ecommerce.bakerymanagement.constants.WebConstants;
import com.ecommerce.bakerymanagement.model.CakeMasterDetails;
import com.ecommerce.bakerymanagement.model.ErrorMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j;

@RequestMapping(value = WebConstants.BASE_PATH_USER_CONFIG_CONTROLLR)
@Api(value = "onlinecakestore", description = " User Operations pertaining to products in Online Store")
@RestController
@Log4j
public class UserConfigurationController {

	@Autowired
	private IBakeryManagementMasterClient iBakeryManagementMasterClient;

/*	@Autowired
	private RequestValidatorHelper requestValidatorHelper;*/

	@ApiOperation(value = "Create a new Order for the Person.", notes = "Also returns a link to retrieve all students with rel - all-students", response = Void.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cake Details Successfully added into the database!!", response = Void.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource!!", response = Void.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden!!", response = Void.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found!!", response = Void.class) })
	@PostMapping(value = WebConstants.CAKE_MASTER_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveOrderDetails(
			@ApiParam("Cake and Person Information for the new Order to be created!!") @RequestBody CakeMasterDetails cakeMasterDetails,
			HttpServletResponse httpServletResponse) {

		try {

			iBakeryManagementMasterClient.saveCakeMasterDetails(cakeMasterDetails);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("");
		}
	}

	@ApiOperation(value = "fetch the latest bulk cake details", notes = "It returns the udpate or latest cake details", response = CakeMasterDetails.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cake Details Successfully fetch from to the database!!", response = CakeMasterDetails.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource!!", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden!!", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found!!", response = ErrorMessage.class) })
	@GetMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CakeMasterDetails> getLastestCakes(HttpServletResponse servletResponse) {

		try {
			return iBakeryManagementMasterClient.getCakeDetails();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("");
		}

		return null;
	}

	@ApiOperation(value = "fetch the cake details by CakeId", notes = "It fetch the details from the existing Cakes Records acc. to the given cakeId", response = CakeMasterDetails.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cake Details Successfully fetch from to the database!!", response = CakeMasterDetails.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource!!", response = ErrorMessage.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden!!", response = ErrorMessage.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found!!", response = ErrorMessage.class) })
	@GetMapping(value = "/cakes/{cakeDetailsId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CakeMasterDetails> getCakeDetailsById(@PathVariable("cakeDetailsId") String cakeDetailsId,
			HttpServletResponse servletResponse) {
		try {
		
			iBakeryManagementMasterClient.getCakeDetailsViaId(cakeDetailsId);

		} catch (Exception e) {
			log.info("getCakeDetailsById", e);
		}
		return null;
	}

}
