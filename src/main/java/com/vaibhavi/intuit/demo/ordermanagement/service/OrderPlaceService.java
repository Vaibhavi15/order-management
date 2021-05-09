package com.vaibhavi.intuit.demo.ordermanagement.service;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;

public interface OrderPlaceService {
	
	public Order placeOrder(Order productOrder);

	public Object makePaymentService(Order productOrder);

}
