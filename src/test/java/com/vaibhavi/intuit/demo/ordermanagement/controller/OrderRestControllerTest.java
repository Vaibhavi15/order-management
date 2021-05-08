package com.vaibhavi.intuit.demo.ordermanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vaibhavi.intuit.demo.ordermanagement.OrdermanagementApplication;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.entity.OrderProduct;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.service.OrderPlaceService;

@ContextConfiguration(classes=OrdermanagementApplication.class)
@WebMvcTest(value = OrderRestController.class)
public class OrderRestControllerTest {
	
	@InjectMocks
	private OrderRestController orderRestController;
	
	@MockBean
	private OrderPlaceService orderPlaceService;

	private MockMvc mockMvc;
	
	@BeforeEach public void initMocks() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderRestController).build();
	}
	
	@Test
	public void test_place_order_return_ok() throws Exception
	{
		Order o = new Order();
		ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
		OrderProduct op = new OrderProduct();
		Product p = new Product();
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);
		
		when(orderPlaceService.placeOrder(Mockito.any(Order.class))).thenReturn(o);
		
		String orderJson = "{\"orderProduct\": [{\"product\" : {\"productId\": 1},\"quantity\" : 1}]}";
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products/orders")
				.accept(MediaType.APPLICATION_JSON).content(orderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.print(result.getResponse().getErrorMessage());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void test_place_order_null_order_return_bad_request() throws Exception
	{
		Order o = new Order();
		ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
		OrderProduct op = new OrderProduct();
		Product p = new Product();
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);
		
		when(orderPlaceService.placeOrder(Mockito.any(Order.class))).thenReturn(o);
		
		String orderJson = "{}";
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products/orders")
				.accept(MediaType.APPLICATION_JSON).content(orderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void test_place_product_null_order_return_bad_request() throws Exception
	{
		Order o = new Order();
		ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
		OrderProduct op = new OrderProduct();
		Product p = new Product();
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);
		
		when(orderPlaceService.placeOrder(Mockito.any(Order.class))).thenReturn(o);
		
		String orderJson = "{\"orderProduct\": [quantity\" : 1}]}";
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products/orders")
				.accept(MediaType.APPLICATION_JSON).content(orderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void test_place_product_id_null_order_return_bad_request() throws Exception
	{
		Order o = new Order();
		ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
		OrderProduct op = new OrderProduct();
		Product p = new Product();
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);
		
		when(orderPlaceService.placeOrder(Mockito.any(Order.class))).thenReturn(o);
		
		String orderJson = "{\"orderProduct\": [{\"product\" : {},\"quantity\" : 1}]}";
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products/orders")
				.accept(MediaType.APPLICATION_JSON).content(orderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
}
