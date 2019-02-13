package com.ecommerce.bakerymanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.bakerymanagement.model.CakeMasterDetails;
import com.ecommerce.bakerymanagement.model.DocumentDetails;

@Component
public class DummyRequestObject {

	public CakeMasterDetails getCakeMasterObject() {
		return CakeMasterDetails.builder().cakeName("Chaco").availability(true).category("Simple")
				.documentDetails(this.getDocumentDetailsList()).build();
	}

	public List<DocumentDetails> getDocumentDetailsList() {

		List<DocumentDetails> documentDetailsList = new ArrayList<>();
		documentDetailsList.add(this.getDocumentObject());
		return documentDetailsList;
	}

	public DocumentDetails getDocumentObject() {
		return DocumentDetails.builder().documentName("Front-Image").documentType("Image").s3Link("dsjdks").build();
	}

}
