package com.vaibhavi.intuit.demo.ordermanagement.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OrderProduct {

	
	@NotNull(message="Product cannot be null")
	private @Valid Product product;
	@NotNull(message="Quantity cannot be null")
	private Integer quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "[product:" + product + ", quantity:" + quantity + "]";
	}

}
 