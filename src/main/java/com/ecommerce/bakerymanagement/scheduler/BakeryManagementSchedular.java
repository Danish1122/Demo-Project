package com.ecommerce.bakerymanagement.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class BakeryManagementSchedular {

	@Scheduled(initialDelay=10000,fixedRate=5000)
	public void useLessSchedular() {
		System.out.println("it running countinuesly!!");
		log.info("it running countinuesly!!");
	}
}
