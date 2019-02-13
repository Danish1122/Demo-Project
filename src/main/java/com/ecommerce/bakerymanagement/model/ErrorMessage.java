package com.ecommerce.bakerymanagement.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {

	private Timestamp date;
	private String discription;
}
