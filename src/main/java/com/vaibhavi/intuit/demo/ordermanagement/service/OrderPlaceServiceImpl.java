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

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderPlaceErrorException;

@Service
public class OrderPlaceServiceImpl implements OrderPlaceService {

	private static final Logger logger=LoggerFactory.getLogger(OrderPlaceService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${order-place-service.url}")
	String ORDER_PLACE_SERVICE_URL;
	
	@Override
	public Order placeOrder(Order productOrder) {
		
		logger.trace("Place Order called");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(productOrder,headers);
	      
	    ResponseEntity<Order> response = restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/posts", HttpMethod.POST, entity, Order.class);
	    
	    if(response.getStatusCode() == HttpStatus.NOT_FOUND || response.getStatusCode() == HttpStatus.BAD_REQUEST)
    	{
	    	logger.error("Products in the Order are invalid");
	    	throw new OrderInvalidException("Products in the Order are invalid");
    	}
	    
	    if(response.getStatusCode() != HttpStatus.OK || response.getBody() == null)
    	{
	    	logger.error("Interal Server Error");
	    	throw new OrderPlaceErrorException("Interal Server Error");
    	}
	    
		logger.trace("Place Order response got");
	    return response.getBody();
	 
	}
}
