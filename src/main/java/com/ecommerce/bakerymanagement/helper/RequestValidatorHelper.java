package com.ecommerce.bakerymanagement.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RequestValidatorHelper {

	@SuppressWarnings("deprecation")
	public Map<String, String> convertStringJsonToMapObject(String dataJson) throws IOException {
		try {

			ObjectMapper mapper = new ObjectMapper();

			Map<String, String> map = new HashMap<String, String>();

			// convert JSON string to Map
			map = mapper.readValue(dataJson, new TypeReference<Map<String, String>>() {
			});

			return map;

		} catch (JsonGenerationException e) {
			log.error("convertStringJsonToMapObject", e);
			throw new JsonGenerationException("convertStringJsonToMapObject", e);

		} catch (JsonMappingException e) {
			log.error("convertStringJsonToMapObject", e);
			throw new JsonMappingException("convertStringJsonToMapObject", e);
		} catch (IOException e) {
			log.error("convertStringJsonToMapObject", e);
			throw new IOException("convertStringJsonToMapObject", e);
		}
	}

}
