package com.vaibhavi.intuit.demo.ordermanagement.controller.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductNotFoundException;
import com.vaibhavi.intuit.demo.ordermanagement.response.ProductErrorResponse;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exception)
	{
		ProductErrorResponse productErrorResponse = new ProductErrorResponse();
		
		productErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		productErrorResponse.setMessage(exception.getMessage());
		productErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(Exception exception)
	{
		ProductErrorResponse productErrorResponse = new ProductErrorResponse();
		
		productErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		productErrorResponse.setMessage(exception.getMessage());
		productErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productErrorResponse);
	}
	

}
