package com.rudrasiva.droolsdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rudrasiva.droolsdemo.model.BusinessRule;
import com.rudrasiva.droolsdemo.model.Product;
import com.rudrasiva.droolsdemo.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/discount", method = RequestMethod.GET, produces = "application/json")
	public Product getQuestions(@RequestParam(required = true) String type) {
		Product product = new Product();
		product.setType(type);
		productService.getProductDiscount(product);
		return product;
	}
	
	@RequestMapping(value="/rules", method = RequestMethod.GET, produces = "application/json")
	public List<BusinessRule> rules() {
		return productService.getRules();
	}
}
