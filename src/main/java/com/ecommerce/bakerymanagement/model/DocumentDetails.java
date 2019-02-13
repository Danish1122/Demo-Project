package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Document(collection = "Bakery_manangement.document_details")
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class DocumentDetails {

	@Id
	@Field(value = "document_id")
	private String documentId;

	@Field(value = "document_name")
	@Indexed
	private String documentName;

	@Field(value = "s3_link")
	@Indexed
	private String s3Link;

	@Field(value = "document_type")
	@Indexed
	private String documentType;

	@Field(value = "created_date")
	private Timestamp createdDate;

	@Field(value = "last_modified_date")
	private Timestamp lastModifiedDate;

	@Field(value = "created_user_id")
	private Long createdUserId;

	@Field("last_modified_user_id")
	private Long lastModifiedUserId;

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("to String Method", e);
		}
		return jsonString;
	}

}
