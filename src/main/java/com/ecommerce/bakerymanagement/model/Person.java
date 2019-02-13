package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * It is Person Bean Class.
 * 
 * @author Danish
 *
 */

@Document(collection = "user.person")
@Component
@AllArgsConstructor
@Data
@NoArgsConstructor
@Log4j2
public class Person {

	@Id
	@Field("person_id")
	private String personId;
	@Field("last_name")
	private String lastName;
	@Field("first_name")
	private String firstName;
	@Field("created_date")
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	private Timestamp createdDate;
	@CreatedBy

	@Field("created_user_id")
	long createdUserId;
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("last_modified_date")
	private Timestamp lastModifiedDate;
	@Field("last_modified_user_id")

	@LastModifiedBy
	private long lastModifiedUserId;

	@DBRef(lazy = true)
	private List<PersonCommunication> personCommunications;

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