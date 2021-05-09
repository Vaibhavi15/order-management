package com.vaibhavi.intuit.demo.ordermanagement.entity;

import javax.validation.constraints.NotNull;

import com.vaibhavi.intuit.demo.ordermanagement.common.Constants;


public class Product {
	
	@NotNull(message = Constants.INVALID_PRODUCT_ID)
	private Integer productId;
	private String productName;
	private float price;
	
	public Product() {
		
	}
	
	public Product(Integer productId, String productName, float price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "[productId:" + productId + ", productName:" + productName + ", price:" + price + "]";
	}
	
}
