package com.vaibhavi.intuit.demo.ordermanagement.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Payment {
	@NotEmpty(message = "Payment method are empty")
	@NotNull(message = "Payment method is null")
	private String paymentMethod;
	@NotEmpty(message = "Payment details are empty")
	@NotNull(message = "Payment details is null")
	private String paymentDetails;
	private String paymentStatus;
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}	
	
}
