package com.vaibhavi.intuit.demo.ordermanagement.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderManagementRestController {

	
	
	@GetMapping("/")
	public String getProductPrice() {
		return "Hello World! Time is " + LocalDateTime.now();
	}
	
}
