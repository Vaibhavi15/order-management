package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class PaymentInvalidException extends RuntimeException {

	public PaymentInvalidException() {
	}

	public PaymentInvalidException(String message) {
		super(message);
	}

	public PaymentInvalidException(Throwable cause) {
		super(cause);
	}

	public PaymentInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
