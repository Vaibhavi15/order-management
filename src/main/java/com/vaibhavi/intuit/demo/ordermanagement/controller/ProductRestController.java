package com.vaibhavi.intuit.demo.ordermanagement.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavi.intuit.demo.ordermanagement.service.ProductPriceService;



@Validated
@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	ProductPriceService productPriceService;
	
	@GetMapping("/price/{productId}")
	public ResponseEntity<Float> getProductPrice(@PathVariable @NotNull Integer productId) {

		return ResponseEntity.status(HttpStatus.OK).body(productPriceService.getProductPrice(productId));
	}
}
