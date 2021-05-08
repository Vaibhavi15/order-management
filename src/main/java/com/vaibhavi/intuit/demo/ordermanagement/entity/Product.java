package com.vaibhavi.intuit.demo.ordermanagement.entity;

import javax.validation.constraints.NotNull;


public class Product {
	
	@NotNull(message = "Product Id cannot be null.")
	private Integer productId;
	private String productName;
	private float price;
	private int stock;
	
	public Product(Integer productId, String productName, float price, int stock) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
