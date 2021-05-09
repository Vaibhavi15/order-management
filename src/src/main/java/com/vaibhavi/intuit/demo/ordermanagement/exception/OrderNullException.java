package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class OrderNullException extends RuntimeException {

	public OrderNullException() {
	}

	public OrderNullException(String message) {
		super(message);
	}

	public OrderNullException(Throwable cause) {
		super(cause);
	}

	public OrderNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
