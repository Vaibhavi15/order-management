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
	private String status;
	
	@Override
	public String toString() {
		return "[orderId:" + orderId + ", orderCreationTime:" + orderCreationTime + ", orderProduct:"
				+ orderProduct + ", status:" + status + "]";
	}
	public Order(){
		
	}
	
	public Order(int orderId, String orderCreationTime, ArrayList<OrderProduct> orderProduct, String status) {
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
	
}
