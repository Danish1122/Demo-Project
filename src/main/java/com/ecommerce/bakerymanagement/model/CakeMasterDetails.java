package com.ecommerce.bakerymanagement.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.querydsl.core.annotations.QueryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * It is cakeMasterDetails Bean Class.
 * 
 * @author Danish
 *
 */

@Document(collection = "Bakery_manangement.cake_master")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@QueryEntity
@Log4j2
@Builder
public class CakeMasterDetails {

	@Id
	@Field("cake_id")
	private String cakeId;

	@Size(min = 3, max = 40, message = "min or max cake name limit exceed")
	@NotNull(message = "cakename ")
	@Field("cake_name")
	private String cakeName;

	@Size(min = 8, max = 40)
	@NotNull
	@Field("flavour")
	private String flavour;

	@Size(min = 4, max = 20)
	@NotNull
	private String category;

	@Min(200)
	@Max(1000)
	@Indexed(direction = IndexDirection.ASCENDING)
	private Double price;

	@NotNull
	private Boolean availability;

	@Min(1)
	@Max(4)
	@NotNull
	private String weight;

	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("created_date")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date createdDate;

	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("last_modified_date")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date lastModifiedDate;

	@Field("created_user_id")

	@CreatedBy
	@Builder.Default
	private Long createdUserId = 1L;

	@Field("last_modified_user_id")
	@LastModifiedBy
	@Builder.Default
	private Long lastModifiedUserId = 1L;

	@DBRef(lazy = true)
	@NotNull
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
