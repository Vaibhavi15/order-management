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
import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductIdInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductPriceGetErrorException;

@Service
public class ProductPriceServiceImpl implements ProductPriceService{

	private static final Logger logger=LoggerFactory.getLogger(ProductPriceService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product-price-service.url}")
	String PRODUCT_PRICE_SERVICE_URL;
	
	@Override
	public Product getProductPrice(Integer productId) {
		
		logger.trace("Get Product Price called");
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	    ResponseEntity<Product> response = restTemplate.exchange(PRODUCT_PRICE_SERVICE_URL + "/" + productId, HttpMethod.GET, entity, Product.class);
	    
	    if(response.getStatusCode() == HttpStatus.NOT_FOUND || response.getStatusCode() == HttpStatus.BAD_REQUEST)
    	{
	    	logger.error(Constants.INVALID_PRODUCT_ID);
	    	throw new ProductIdInvalidException(Constants.INVALID_PRODUCT_ID);
    	}
	    
	    if(response.getStatusCode() != HttpStatus.OK || response.getBody() == null)
    	{
	    	logger.error(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
	    	throw new ProductPriceGetErrorException(Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    	}
	    
		logger.trace("Get Product Price response got");
	    return response.getBody();
	}
	
}
