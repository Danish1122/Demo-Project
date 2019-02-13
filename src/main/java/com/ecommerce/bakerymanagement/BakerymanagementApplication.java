package com.ecommerce.bakerymanagement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "com.ecommerce.bakerymanagement" })
@EnableMongoRepositories({ "com.ecommerce.bakerymanagement.mongodb.repository" })
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
@Log4j2
@EnableWebMvc
//@EnableRabbit
public class BakerymanagementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BakerymanagementApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BakerymanagementApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			log.info("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();

			List<String> beanNames1 = Arrays.stream(beanNames).sorted().collect(Collectors.toList());
			beanNames1.forEach(beanName -> log.info(beanName));
		};
	}

}
