package com.vaibhavi.intuit.demo.ordermanagement.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vaibhavi.intuit.demo.ordermanagement.common.Constants;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Payment;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderPlaceErrorException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.PaymentInvalidException;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger logger=LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${payment-validation.url}")
	String PAYMENT_VALIDATION_SERVICE_URL;
	
	@Value("${payment-service.url}")
	String PAYMENT_SERVICE_URL;
	
	@Override
	public Payment validatePaymentMethod(Payment payment) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Payment> entity = new HttpEntity<Payment>(payment,headers);
	      
	    ResponseEntity<Payment> response = restTemplate.exchange(PAYMENT_VALIDATION_SERVICE_URL + "/validate", HttpMethod.POST, entity, Payment.class);

	    if(response == null || response.getBody() == null)
	    {
	    	logger.error(Constants.SERVICE_NULL_RESPONSE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    }
	    if(response.getStatusCode() == HttpStatus.NOT_FOUND || response.getStatusCode() == HttpStatus.BAD_REQUEST)
    	{
	    	logger.error(Constants.INVALID_PAYMENT_METHOD);
	    	throw new PaymentInvalidException(Constants.INVALID_PAYMENT_METHOD);
    	}
	    
	    if(response.getStatusCode() != HttpStatus.OK || response.getBody().getPaymentStatus().equals("FAILED"))
    	{
	    	logger.error(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    	}
	    
	    logger.trace("Payment details validated");
		return response.getBody();
	}

	@Override
	public Order makePayment(Order order) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(order,headers);
	      
	    ResponseEntity<Order> response = restTemplate.exchange(PAYMENT_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class);
	    
	    if(response == null || response.getBody() == null)
	    {
	    	logger.error(Constants.SERVICE_NULL_RESPONSE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    }
	    if(response.getStatusCode() == HttpStatus.NOT_FOUND || response.getStatusCode() == HttpStatus.BAD_REQUEST)
    	{
	    	logger.error(Constants.INVALID_PAYMENT_METHOD);
	    	throw new PaymentInvalidException(Constants.INVALID_PAYMENT_METHOD);
    	}
	    
	    if(response.getStatusCode() != HttpStatus.OK )
    	{
	    	logger.error(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    	}
	    
		logger.trace("Payment response got");
	    return response.getBody();
	}

}
