package com.vaibhavi.intuit.demo.ordermanagement.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavi.intuit.demo.ordermanagement.entity.Product;
import com.vaibhavi.intuit.demo.ordermanagement.service.ProductPriceService;



@Validated
@RestController
@RequestMapping("/products")
public class ProductRestController {

	private static final Logger logger=LoggerFactory.getLogger(ProductRestController.class);

	@Autowired
	private ProductPriceService productPriceService;
	
	@GetMapping("/price/{productId}")
	public ResponseEntity<Product> getProductPrice(@PathVariable @NotNull Integer productId) {
		
		logger.info("Get Price called for " + productId);

		Product response = productPriceService.getProductPrice(productId);
		
		if(response != null)
		{
			logger.debug("Product id is " + response.getProductId() + " price is " + response.getPrice());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
