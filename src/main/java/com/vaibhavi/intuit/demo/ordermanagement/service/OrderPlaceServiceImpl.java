package com.vaibhavi.intuit.demo.ordermanagement.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;

@Service
public class OrderPlaceServiceImpl implements OrderPlaceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${order-place-service.url}")
	String ORDER_PLACE_SERVICE_URL;
	
	@Override
	public Order placeOrder(Order productOrder) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(productOrder,headers);
	      
	    return restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/posts", HttpMethod.POST, entity, Order.class).getBody();
	 
	}
}
