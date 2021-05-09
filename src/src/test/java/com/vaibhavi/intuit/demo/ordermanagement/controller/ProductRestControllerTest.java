package com.vaibhavi.intuit.demo.ordermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.service.ProductPriceService;

@WebMvcTest(value = ProductRestController.class)
public class ProductRestControllerTest {
	
	@InjectMocks
	private ProductRestController productRestController;
	
	@MockBean
	private ProductPriceService productPriceService;

	private MockMvc mockMvc;
	
	@BeforeEach public void initMocks() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productRestController).build();
	}
	
	@Test
	public void test_get_product_price_by_id() throws Exception
	{
		Product p = new Product();
		Integer i = 1;
		p.setProductId(1);
		Mockito.when(productPriceService.getProductPrice(i)).thenReturn(p);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products/price/1").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void test_get_product_price_product_id_null() throws Exception
	{
		Product p = new Product();
		Integer i = null;
		p.setProductId(1);
		Mockito.when(productPriceService.getProductPrice(i)).thenReturn(p);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products/price/").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
	
}
