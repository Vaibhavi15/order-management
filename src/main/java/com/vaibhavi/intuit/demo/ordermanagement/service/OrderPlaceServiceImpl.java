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
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderPlaceErrorException;

@Service
public class OrderPlaceServiceImpl implements OrderPlaceService {

	private static final Logger logger=LoggerFactory.getLogger(OrderPlaceService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private PaymentService paymentService;
	
	@Value("${order-place-service.url}")
	String ORDER_PLACE_SERVICE_URL;
	
	private Payment isPaymentValid;
	
	private Order paymentOrderResponse;
	
	@Override
	public Order placeOrder(Order productOrder) {
		
		paymentOrderResponse = productOrder;
		
		logger.trace("Place Order called");
		
		paymentOrderResponse = makePaymentService(productOrder);
		
		logger.debug("Payment complete");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(paymentOrderResponse,headers);
	      
	    ResponseEntity<Order> response = restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class);
	    
	    if(response == null || response.getBody() == null)
	    {
	    	logger.error(Constants.SERVICE_NULL_RESPONSE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    }
	    
	    if(response.getStatusCode() == HttpStatus.NOT_FOUND || response.getStatusCode() == HttpStatus.BAD_REQUEST)
    	{
	    	logger.error(Constants.INVALID_PRODUCT_ORDER);
	    	throw new OrderInvalidException(Constants.INVALID_PRODUCT_ORDER);
    	}
	    
	    if(response.getStatusCode() != HttpStatus.OK)
    	{
	    	logger.error(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    	}
	    
		logger.trace("Place Order response got");
	    return response.getBody();
	 
	}
	
	@Override
	public Order makePaymentService(Order order)
	{
		try
		{
			isPaymentValid = paymentService.validatePaymentMethod(order.getPayment());
			order.setPayment(isPaymentValid);
			paymentOrderResponse = paymentService.makePayment(order);
		}
		catch(OrderInvalidException e)
		{
			logger.error(Constants.INVALID_PAYMENT_DETAILS);
	    	throw new OrderInvalidException(Constants.INVALID_PAYMENT_DETAILS);
		}
		catch(Exception e)
		{
			logger.error(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    	throw new OrderPlaceErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
		}
		
		return order;
	}
}
