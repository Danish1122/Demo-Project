package com.ecommerce.bakerymanagement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecommerce.bakerymanagement.constants.WebConstants;
import com.ecommerce.bakerymanagement.controller.UserConfigurationController;
import com.ecommerce.bakerymanagement.helper.CommonHelper;
import com.ecommerce.bakerymanagement.model.CakeMasterDetails;
import com.ecommerce.bakerymanagement.mongodb.repository.CakeRepository;
import com.ecommerce.bakerymanagement.mongodb.repository.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserConfigurationController.class)
public class UserConfigurationControllerApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks	
	private UserConfigurationController userConfigurationController;

	@InjectMocks
	private DummyRequestObject dummyRequestObject;

	@Mock
	private CakeRepository cakeRepository;

	@Mock
	private DocumentRepository documentRepository;

	@Mock
	private CommonHelper commonHelper;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private MongoOperations mongoOperations;

	@Before
	public void intialSetUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userConfigurationController).build();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testSaveOrderDetailsService() throws Exception {

		CakeMasterDetails cakeMasterDetails = dummyRequestObject.getCakeMasterObject();
		mockMvc.perform(
				MockMvcRequestBuilders.get(WebConstants.BASE_PATH_USER_CONFIG_CONTROLLR + WebConstants.ORDER_DETAILS)
						.content(objectMapper.writeValueAsString(cakeMasterDetails))
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());

	}
}
