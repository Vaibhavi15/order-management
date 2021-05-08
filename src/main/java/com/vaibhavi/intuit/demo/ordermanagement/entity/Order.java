package com.vaibhavi.intuit.demo.ordermanagement.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Order {
	private int orderId;
	private String orderCreationTime;
	@NotEmpty(message = "Order is empty, no products specified")
	@NotNull(message = "Order is null, no products specified")
	private List<@Valid OrderProduct> orderProduct;
	private String status;
	
	public Order(int orderId, String orderCreationTime, List<OrderProduct> orderProduct, String status) {
		this.orderId = orderId;
		this.orderCreationTime = orderCreationTime;
		this.orderProduct = orderProduct;
		this.status = status;
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
	public List<OrderProduct> getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(List<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
