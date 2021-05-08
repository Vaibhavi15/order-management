package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class OrderInvalidException extends RuntimeException {

	public OrderInvalidException() {
	}

	public OrderInvalidException(String message) {
		super(message);
	}

	public OrderInvalidException(Throwable cause) {
		super(cause);
	}

	public OrderInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
