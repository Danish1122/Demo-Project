package com.ecommerce.bakerymanagement.scheduler;

import org.springframework.stereotype.Component;

@Component
public class FileUploadQueue {

	/* @Autowired(required=false)
	private AmqpTemplate amqpTemplate;

	@Autowired
	private RabbitMQConfig rabbitMQConfig;

	public void produceMsg(String msg) {
		amqpTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getQueueName(), msg);
		System.out.println("Send msg = " + msg);
	}*/

}
