package com.vaibhavi.intuit.demo.ordermanagement.entity;

import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Order {
	
	private int orderId;
	private String orderCreationTime;

	@NotEmpty(message = "Order is empty, no products specified")
	@NotNull(message = "Order is null, no products specified")
	private ArrayList<@Valid OrderProduct> orderProduct;
	@NotEmpty(message = "Payment details are empty")
	@NotNull(message = "Payment details is null")
	private Payment payment;
	private String status;
	
	@Override
	public String toString() {
		return "[orderId:" + orderId + ", orderCreationTime:" + orderCreationTime + ", orderProduct:"
				+ orderProduct + ", status:" + status + "]";
	}
	public Order(){
		
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderCreationTime() {
		return orderCreationTime;
	}
	public void setOrderCreationTime(String orderCreationTime) {
		this.orderCreationTime = orderCreationTime;
	}
	public ArrayList<OrderProduct> getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(ArrayList<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
