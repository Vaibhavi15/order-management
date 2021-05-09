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
import com.vaibhavi.intuit.demo.ordermanagement.exception.OrderPlaceErrorException;
import com.vaibhavi.intuit.demo.ordermanagement.exception.PaymentInvalidException;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {
	
	@Mock
	private RestTemplate restTemplate;
		
	@Value("${payment-validation.url}")
	String PAYMENT_VALIDATION_SERVICE_URL;
	
	@Value("${payment-service.url}")
	String PAYMENT_SERVICE_URL;
	
	@InjectMocks
	private PaymentService paymentService = new PaymentServiceImpl();
	
	@BeforeEach public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void test_validate_payment_returns_ok()
	{
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Payment> entity = new HttpEntity<Payment>(o.getPayment(),headers);
		
	    when(restTemplate.exchange(PAYMENT_VALIDATION_SERVICE_URL + "/validate", HttpMethod.POST, entity, Payment.class)).thenReturn(new ResponseEntity<Payment>(o.getPayment(), HttpStatus.OK));
	    
	    assertEquals(o.getPayment(), paymentService.validatePaymentMethod(o.getPayment()));
	}
	
	@Test
	void test_validate_payment_returns_bad_request()
	{
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Payment> entity = new HttpEntity<Payment>(o.getPayment(),headers);
		
	    when(restTemplate.exchange(PAYMENT_VALIDATION_SERVICE_URL + "/validate", HttpMethod.POST, entity, Payment.class)).thenReturn(new ResponseEntity<Payment>(o.getPayment(), HttpStatus.BAD_REQUEST));
	    
	    assertThrows(PaymentInvalidException.class, () -> paymentService.validatePaymentMethod(o.getPayment()));
	}
	
	@Test
	void test_validate_payment_failed_returns_internal_error()
	{
		Order o = getOrderObject();		
		o.getPayment().setPaymentStatus("FAILED");
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Payment> entity = new HttpEntity<Payment>(o.getPayment(),headers);
		
	    when(restTemplate.exchange(PAYMENT_VALIDATION_SERVICE_URL + "/validate", HttpMethod.POST, entity, Payment.class)).thenReturn(new ResponseEntity<Payment>(o.getPayment(), HttpStatus.OK));
	    
	    assertThrows(OrderPlaceErrorException.class, () -> paymentService.validatePaymentMethod(o.getPayment()));
	}
	
	
	@Test
	void test_make_payment_returns_ok()
	{
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
		
	    when(restTemplate.exchange(PAYMENT_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(new ResponseEntity<Order>(o, HttpStatus.OK));
	    
	    assertEquals(o, paymentService.makePayment(o));
	}
	
	@Test
	void test_make_payment_returns_bad_request()
	{
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
		
	    when(restTemplate.exchange(PAYMENT_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(new ResponseEntity<Order>(o, HttpStatus.BAD_REQUEST));
	    
	    assertThrows(PaymentInvalidException.class, () -> paymentService.makePayment(o));
	}
	
	@Test
	void test_make_payment_returns_server_error()
	{
		Order o = getOrderObject();		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    HttpEntity<Order> entity = new HttpEntity<Order>(o,headers);
		
	    when(restTemplate.exchange(PAYMENT_SERVICE_URL + "/orders", HttpMethod.POST, entity, Order.class)).thenReturn(null);
	    
	    assertThrows(OrderPlaceErrorException.class, () -> paymentService.makePayment(o));
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
		pay.setPaymentStatus("SUCCESS");
		o.setPayment(pay);
		p.setProductId(1);
		op.setProduct(p);
		op.setQuantity(1);
		products.add(op);
		o.setOrderProduct(products);

		return o;
	}
}
