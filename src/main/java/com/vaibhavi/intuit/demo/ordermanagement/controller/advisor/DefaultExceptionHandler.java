package com.vaibhavi.intuit.demo.ordermanagement.controller.advisor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductNotFoundException;
import com.vaibhavi.intuit.demo.ordermanagement.response.ProductErrorResponse;
import com.vaibhavi.intuit.demo.ordermanagement.response.ValidationErrorResponse;

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
	public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
	    
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		Map<String, String> errors = new HashMap<>();
		
	    exception.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    validationErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	    validationErrorResponse.setMessage("Input Validation Error");
	    validationErrorResponse.setErrors(errors);
	    validationErrorResponse.setTimeStamp(System.currentTimeMillis());
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
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
