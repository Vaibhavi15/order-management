package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class ProductIdInvalidException extends RuntimeException{

	public ProductIdInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductIdInvalidException(String message) {
		super(message);
	}

	public ProductIdInvalidException(Throwable cause) {
		super(cause);
	}

}
