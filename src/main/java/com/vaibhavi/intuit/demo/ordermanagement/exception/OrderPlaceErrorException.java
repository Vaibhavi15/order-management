package com.vaibhavi.intuit.demo.ordermanagement.exception;

public class OrderPlaceErrorException extends RuntimeException{

	public OrderPlaceErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderPlaceErrorException(String message) {
		super(message);
	}

	public OrderPlaceErrorException(Throwable cause) {
		super(cause);
	}
	

}
