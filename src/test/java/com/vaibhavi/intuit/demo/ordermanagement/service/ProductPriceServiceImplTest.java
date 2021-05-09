package com.vaibhavi.intuit.demo.ordermanagement.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductIdInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.ProductPriceGetErrorException;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceServiceImplTest {

	@Mock
	private RestTemplate restTemplate;
	
	@Value("${product-price-service.url}")
	String PRODUCT_PRICE_SERVICE_URL;
	
	@InjectMocks
	private ProductPriceService productPriceService = new ProductPriceServiceImpl();
	
	@BeforeEach public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	/*@Test
	public void test_product_price_service_returns_ok()
	{
		Product p = new Product();
		Integer i = 1;
		p.setProductId(1);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	    
	    when(restTemplate.exchange(PRODUCT_PRICE_SERVICE_URL + "/" + i, HttpMethod.GET, entity, Product.class)).thenReturn(new ResponseEntity<Product>(p, HttpStatus.OK));
	
	    assertEquals(p, productPriceService.getProductPrice(i));
	}*/
	
	@Test
	public void test_product_price_service_returns_bad_request()
	{
		Product p = new Product();
		Integer i = 1;
		p.setProductId(1);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	    
	    when(restTemplate.exchange(PRODUCT_PRICE_SERVICE_URL + "/" + i, HttpMethod.GET, entity, Product.class)).thenReturn(new ResponseEntity<Product>(p, HttpStatus.BAD_REQUEST));
	
	    assertThrows(ProductIdInvalidException.class, () -> productPriceService.getProductPrice(i));

	}
	
	@Test
	public void test_product_price_service_returns_internal_server_error()
	{
		Product p = new Product();
		Integer i = 1;
		p.setProductId(1);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	    
	    when(restTemplate.exchange(PRODUCT_PRICE_SERVICE_URL + "/" + i, HttpMethod.GET, entity, Product.class)).thenReturn(new ResponseEntity<Product>(p, HttpStatus.INTERNAL_SERVER_ERROR));
	
	    assertThrows(ProductPriceGetErrorException.class, () -> productPriceService.getProductPrice(i));

	}
}
