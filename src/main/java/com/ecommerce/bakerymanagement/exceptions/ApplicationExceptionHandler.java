package com.ecommerce.bakerymanagement.exceptions;

import java.sql.Timestamp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.bakerymanagement.model.ErrorMessage;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class, BakeryConfigurationException.class,
			OrderConfigurationException.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
			log.error(ex.getMessage());
		}

		ErrorMessage errorMessage = ErrorMessage.builder().date(new Timestamp(System.currentTimeMillis()))
				.discription(errorMessageDescription).build();
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { RestClientException.class })
	public ResponseEntity<Object> handleRestClientException(RestClientException restClientException,
			WebRequest webRequest) {

		String errorMessageDescription = restClientException.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = restClientException.toString();
			log.error(restClientException.getMessage());
		}

		ErrorMessage errorMessage = ErrorMessage.builder().date(new Timestamp(System.currentTimeMillis()))
				.discription(errorMessageDescription).build();
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
