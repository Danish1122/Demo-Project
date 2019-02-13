package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
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

@Document(collection = "Bakery_manangement.snack_master")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class SnacksMasterDetails {

	@Id
	@Field("snack_master_id")
	private String snackMasterDetailsId;

	private String name;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String type;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String flavour;

	private Boolean availability;

	private String weight;

	@Field("created_date")
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@CreatedDate
	private Timestamp createdDate;

	@Field("last_modified_Date")
	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@LastModifiedDate
	private Timestamp lastModifiedDate;

	@Field("created_user_id")
	@CreatedBy
	@NotNull
	private Long createdUserId;

	@Field("last_modified_user_id")
	@LastModifiedBy
	@NotNull
	private Long lastModifiedUserId;

	@DBRef(lazy = true)
	@NotNull
	@CascadeSave
	private List<DocumentDetails> documentDetails;

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
