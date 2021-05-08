package com.vaibhavi.intuit.demo.ordermanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Post;
//import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class ProductPriceServiceImpl implements ProductPriceService{

	@Value("${product-price-service.url}")
	String PRICE_SERVICE_URL;
	
	@Override
	public float getProductPrice(int productId) {
		WebClient client = WebClient.builder()
				  .baseUrl(PRICE_SERVICE_URL)
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				  .build();
		
		Mono<Post> response = client.get()
		        .uri("/posts/" + productId)
		        .retrieve()
		        .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
		                clientResponse -> null)
		        .bodyToMono(Post.class);
		
		if(response == null)
		{
			throw new ProductNotFoundException("Product with ID " + productId + "not found");
		}
		
		return response.block().getUserId();
	}
	
}
