package com.vaibhavi.intuit.demo.ordermanagement.controller.advisor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vaibhavi.intuit.demo.ordermanagement.common.Constants;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.PaymentInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductIdInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.response.OrderErrorResponse;
import com.vaibhavi.intuit.demo.ordermanagement.response.PaymentErrorResponse;
import com.vaibhavi.intuit.demo.ordermanagement.response.ProductErrorResponse;
import com.vaibhavi.intuit.demo.ordermanagement.response.ValidationErrorResponse;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	private static final Logger logger=LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductIdInvalidException exception)
	{
		ProductErrorResponse productErrorResponse = new ProductErrorResponse();
		
		productErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		productErrorResponse.setMessage(exception.getMessage());
		productErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		logger.error(exception.getMessage());

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
	    validationErrorResponse.setMessage(Constants.INPUT_VALIDATION_ERROR_MESSAGE);
	    validationErrorResponse.setErrors(errors);
	    validationErrorResponse.setTimeStamp(System.currentTimeMillis());
	    
		logger.error(exception.getMessage());
		logger.debug(validationErrorResponse.getErrors().toString());

		
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<OrderErrorResponse> handleOrderInvalidException(OrderInvalidException exception)
	{
		OrderErrorResponse orderErrorResponse = new OrderErrorResponse();
		
		orderErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		orderErrorResponse.setMessage(exception.getMessage());
		orderErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		logger.error(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderErrorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<PaymentErrorResponse> handlePaymentInvalidException(PaymentInvalidException exception)
	{
		PaymentErrorResponse paymentErrorResponse = new PaymentErrorResponse();
		
		paymentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		paymentErrorResponse.setMessage(exception.getMessage());
		paymentErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		logger.error(exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paymentErrorResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(Exception exception)
	{
		ProductErrorResponse productErrorResponse = new ProductErrorResponse();
		
		productErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		productErrorResponse.setMessage(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
		productErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		logger.error(exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productErrorResponse);
	}
	
	

}
