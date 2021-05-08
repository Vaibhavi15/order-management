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
//import com.vaibhavi.intuit.demo.ordermanagement.entity.Post;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;

@Service
public class ProductPriceServiceImpl implements ProductPriceService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product-price-service.url}")
	String PRODUCT_PRICE_SERVICE_URL;
	
	@Override
	public Product getProductPrice(int productId) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	    return restTemplate.exchange(PRODUCT_PRICE_SERVICE_URL + "/" + productId, HttpMethod.GET, entity, Product.class).getBody();
	}
	
}
