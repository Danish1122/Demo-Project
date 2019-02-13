package com.ecommerce.bakerymanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ecommerce.bakerymanagement.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(this.metaData());
	}

	/*@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(CLIENT_ID).clientSecret(CLIENT_SECRET)
				.scopeSeparator(" ").useBasicAuthenticationWithAccessCodeGrant(true).build();
	}*/

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Spring Boot REST API")
				.description("\"Spring Boot REST API for Online Bakery Store\"").version("1.0.0")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.contact(new Contact("Danish Sharma", "https://rajabakery.com/about/", "danish@gmail.com")).build();
	}

	/*private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
				.tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
				.build();

		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes())).build();
		return oauth;
	}*/

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations"),
				new AuthorizationScope("foo", "Access foo API") };
		return scopes;
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
