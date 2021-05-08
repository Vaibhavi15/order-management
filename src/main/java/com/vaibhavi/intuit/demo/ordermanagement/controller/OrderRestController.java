package com.vaibhavi.intuit.demo.ordermanagement.controller;

import javax.validation.Valid;

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
	
	@Autowired
	OrderPlaceService orderPlaceService;

	@PostMapping("/orders")
	public Order placeOrder(@Valid @RequestBody Order productOrder)
	{
		if(productOrder == null)
		{
			throw new OrderNullException("Order is null, no products specified");
		}
		
		productOrder.setOrderId(0);
		
		return orderPlaceService.placeOrder(productOrder);
	}
}
