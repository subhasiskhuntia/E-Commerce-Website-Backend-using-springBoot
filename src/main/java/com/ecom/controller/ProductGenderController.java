package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Product;
import com.ecom.serviceImpl.ProductGenderServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("api/gender")
public class ProductGenderController {
	@Autowired
	ProductGenderServiceImpl genderServiceImpl;
	@GetMapping(value = "products/{id}")
	public List<Product> showProducts(@PathVariable int id) {
		return genderServiceImpl.findGenderByid(id).getProducts();
	}
}
