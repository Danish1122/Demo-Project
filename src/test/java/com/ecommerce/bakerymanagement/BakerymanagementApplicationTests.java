package com.ecommerce.bakerymanagement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableMongoRepositories(basePackages= {"com.ecommerce.bakerymanagement.mongodb.repository"})
public class BakerymanagementApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	
	

	@Test
	public void getPersonByIdShouldReturnOk() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
