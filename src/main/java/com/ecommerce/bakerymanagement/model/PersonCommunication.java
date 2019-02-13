package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * It is PersonCommunication Bean Class.
 * 
 * @author Danish
 *
 */
@Document(collection = "user.person_communication")
@JsonIgnoreProperties
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PersonCommunication {

	@Id
	@Field("person_communication_id")
	private String personCommunicationId;
	@Field("communication_type")
	private long communicationType;
	@Field("sub_type")
	private long subType;
	@Field("communication_value")
	private String communicationValue;
	private boolean active;
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("created_date")
	Timestamp createdDate;
	@CreatedBy
	@Field("created_user_id")
	private long createdUserId;
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("last_modified_date")
	private Timestamp lastModifiedDate;

	@Field("last_modified_user_id")
	@LastModifiedBy
	private long lastModifiedUserId;

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