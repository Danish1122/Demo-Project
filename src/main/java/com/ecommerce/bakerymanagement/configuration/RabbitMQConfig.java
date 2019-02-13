package com.ecommerce.bakerymanagement.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RabbitMQConfig {

	@Value("${cake.rabbitmq.queue}")
	private String queueName;

	@Value("${cake.rabbitmq.exchange}")
	private String exchange;

	@Value("${cake.rabbitmq.routingkey}")
	private String routingKey;

}
