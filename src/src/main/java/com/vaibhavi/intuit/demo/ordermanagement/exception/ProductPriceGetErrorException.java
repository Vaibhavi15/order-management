package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class ProductPriceGetErrorException extends RuntimeException {

	public ProductPriceGetErrorException() {
	}

	public ProductPriceGetErrorException(String message) {
		super(message);
	}

	public ProductPriceGetErrorException(Throwable cause) {
		super(cause);
	}

	public ProductPriceGetErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductPriceGetErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
