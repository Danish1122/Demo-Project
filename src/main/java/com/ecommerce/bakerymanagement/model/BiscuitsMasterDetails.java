package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
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

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * It is BiscuitsMasterDetails Bean Class.
 * 
 * @author Danish
 *
 */
@Document(collection = "Bakery_manangement.biscuits_master")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class BiscuitsMasterDetails {

	@Id
	@Field("biscuits_Master_id")
	@ApiModelProperty(notes = "The auto-generated version of the product.")
	private String biscuitsMasterDetailsId;

	@NotNull(message = "category field can't be null")
	@Size(min = 4, max = 15, message = "min or max biscuits name limit exceed")
	@ApiModelProperty(notes = "The category of biscuits like kaju,plain etc.", example = "Custom Picture Cake.", required = true, position = 1)
	private String category;

	@NotNull(message = "flavour field can't be null")
	@ApiModelProperty(notes = "The flavour of biscuits like salt,sweet,mix etc.", example = "Salt Flour,Sweet Flavour, Mix flavour", position = 2)
	@Size(min = 4, max = 15, message = "min or max flavour name limit exceed")
	private String flavour;

	@NotNull(message = "availbility field can't be null")
	@ApiModelProperty(notes = "The availability of the cake.", example = "true or false", position = 3)
	private Boolean availability;

	@NotNull(message = "weight field can't be null")
	@Size(min = 1, max = 5, message = "min or max cake weight limit exceed")
	@ApiModelProperty(notes = "The weight of the cake in Grams and Kg's only.", example = "1Kg,500gm", position = 4)
	private String weight;

	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("created_date")
	@CreatedDate
	private Timestamp createdDate;

	@JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss aa")
	@Field("last_modified_date")
	@LastModifiedDate
	private Timestamp lastModifiedDate;

	@Field("created_user_id")
	@CreatedBy
	private Long createdUserId = 1L;

	@Field("last_modified_user_id")
	@LastModifiedBy
	private Long lastModifiedUserId = 1L;

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
