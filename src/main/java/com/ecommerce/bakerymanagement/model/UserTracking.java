package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.ecommerce.bakerymanagement.mongodb.repository.CascadeSave;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Document(collection = "user.user_tracking")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class UserTracking {

	@Id
	@Field("user_tracking_id")
	private String userTrackingId;

	@DBRef(lazy = true)
	@CascadeSave
	private List<BiscuitsMasterDetails> biscuitsMasterDetails;

	@DBRef(lazy = true)
	@CascadeSave
	private List<CakeMasterDetails> cakeMasterDetails;

	@DBRef(lazy = true)
	@CascadeSave
	private List<SnacksMasterDetails> snacksMasterDetails;

	@DBRef(lazy = true)
	private Person person;

	@Field("created_date")
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@CreatedDate
	private Timestamp createdDate;

	@Field("last_modified_date")
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@LastModifiedDate
	private Timestamp lastModifiedDate;

	@Field("created_user_id")
	@CreatedBy
	private Long createdUserId = 1L;

	@Field("last_modified_user_id")
	private Long lastModifiedUserId = 1L;

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
