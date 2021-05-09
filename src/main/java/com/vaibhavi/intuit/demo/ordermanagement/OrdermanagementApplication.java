package com.vaibhavi.intuit.demo.ordermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrdermanagementApplication {

	private static final Logger logger=LoggerFactory.getLogger(OrdermanagementApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementApplication.class, args);
		logger.trace("Starting Order Management Application");
	}
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

}
