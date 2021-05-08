package com.vaibhavi.intuit.demo.ordermanagement.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OrderProduct {

	@NotNull(message="Product cannot be null")
	private @Valid Product product;
	private int quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
 