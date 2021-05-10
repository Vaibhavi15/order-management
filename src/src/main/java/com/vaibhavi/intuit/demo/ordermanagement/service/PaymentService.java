package com.vaibhavi.intuit.demo.ordermanagement.service;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Payment;

public interface PaymentService {

	public Payment validatePaymentMethod(Payment payment);
	public Order makePayment(Order order);
}
