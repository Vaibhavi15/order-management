package com.vaibhavi.intuit.demo.ordermanagement.service;

import org.springframework.stereotype.Service;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;

@Service
public class OrderPlaceServiceImpl implements OrderPlaceService {

	@Override
	public Order placeOrder(Order productOrder) {
		return productOrder;
	}
}
