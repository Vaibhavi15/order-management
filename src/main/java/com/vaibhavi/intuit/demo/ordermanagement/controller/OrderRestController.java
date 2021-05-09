package com.vaibhavi.intuit.demo.ordermanagement.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderNullException;
import com.vaibhavi.intuit.demo.ordermanagement.service.OrderPlaceService;


@RestController
@RequestMapping("/products")
public class OrderRestController {
	
	private static final Logger logger=LoggerFactory.getLogger(OrderRestController.class);

	@Autowired
	private OrderPlaceService orderPlaceService;

	@PostMapping("/orders")
	public Order placeOrder(@Valid @RequestBody Order productOrder)
	{
		
		if(productOrder == null)
		{
			logger.error("Order is null, no products specified");
			throw new OrderNullException("Order is null, no products specified");
		}
		
		logger.info("Place Order called for " + productOrder.toString());
		productOrder.setOrderId(0);		
		Order response = orderPlaceService.placeOrder(productOrder);
		
		if(response != null)
		{
			logger.debug("Order id is " + response.getOrderId() + " status is " + response.getStatus());
		}
		
		return response;
	}
}
