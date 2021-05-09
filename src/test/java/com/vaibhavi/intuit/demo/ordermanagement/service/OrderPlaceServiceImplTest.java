package com.vaibhavi.intuit.demo.ordermanagement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.vaibhavi.intuit.demo.ordermanagement.entity.Order;
import com.vaibhavi.intuit.demo.ordermanagement.entity.OrderProduct;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Payment;
import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderInvalidException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderPlaceErrorException;

@RunWith(MockitoJUnitRunner.class)
public class OrderPlaceServiceImplTest {

	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private PaymentService paymentService;
		
	@Value("${order-place-service.url}")
	String ORDER_PLACE_SERVICE_URL;
	
	@InjectMocks
	private OrderPlaceService orderPlaceService = new OrderPlaceServiceImpl();
	
	@BeforeEach public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void test_order_place_service_returns_ok() {
		
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
		
	    when(orderPlaceService.makePaymentService(o)).thenReturn(o);
	    when(restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(new ResponseEntity<Order>(o, HttpStatus.OK));
	    
	    
	    assertEquals(o, orderPlaceService.placeOrder(o));
	}
	
	@Test
	public void test_order_place_service_returns_bad_request() {
		
		Order o = getOrderObject();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
	    when(orderPlaceService.makePaymentService(o)).thenReturn(o);
	    when(restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(new ResponseEntity<Order>(o, HttpStatus.BAD_REQUEST));
	    
	    assertThrows(OrderInvalidException.class, () -> orderPlaceService.placeOrder(o));
	}
	
	@Test
	public void test_order_place_service_returns_internal_server_error() {
		Order o = getOrderObject();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
	    when(orderPlaceService.makePaymentService(o)).thenReturn(o);
	    when(restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(new ResponseEntity<Order>(o, HttpStatus.INTERNAL_SERVER_ERROR));
	    
	    assertThrows(OrderPlaceErrorException.class, () -> orderPlaceService.placeOrder(o));
	}
	
	@Test
	public void test_order_place_service_returns_null_internal_server_error() {
		Order o = getOrderObject();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
	    when(orderPlaceService.makePaymentService(o)).thenReturn(o);
	    when(restTemplate.exchange(ORDER_PLACE_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(null);
	    
	    assertThrows(OrderPlaceErrorException.class, () -> orderPlaceService.placeOrder(o));
	}
	
	@Test 
	 void test_make_payment_method_returns_order() {
		Order o = getOrderObject();
		when(paymentService.validatePaymentMethod(o.getPayment())).thenReturn(o.getPayment());
		when(paymentService.makePayment(o)).thenReturn(o);
		
		assertEquals(o, paymentService.makePayment(o));
	}
	
	@Test
	void test_make_payment_method_returns_order_exception() {
		Order o = getOrderObject();
		when(paymentService.validatePaymentMethod(o.getPayment())).thenReturn(o.getPayment());
		when(paymentService.makePayment(o)).thenThrow(new OrderInvalidException());
		
		assertThrows(OrderInvalidException.class, () -> orderPlaceService.makePaymentService(o));
	}
	
	@Test
	void test_make_payment_method_returns_exception() {
		Order o = getOrderObject();
		when(paymentService.validatePaymentMethod(o.getPayment())).thenThrow(new RuntimeException());
		when(paymentService.makePayment(o)).thenReturn(o);
		
		assertThrows(OrderPlaceErrorException.class, () -> orderPlaceService.makePaymentService(o));
	}
	
	public Order getOrderObject()
	{
		Order o = new Order();
		ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
		OrderProduct op = new OrderProduct();
		Product p = new Product();
		Payment pay = new Payment();
		pay.setPaymentMethod("Credit card");
		pay.setPaymentDetails("123456789");
		o.setPayment(pay);
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);
		
		return o;
	}
}
