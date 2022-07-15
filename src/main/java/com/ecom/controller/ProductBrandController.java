package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Product;
import com.ecom.entity.ProductBrand;
import com.ecom.serviceImpl.ProductBrandServiceImpl;

@RestController
@RequestMapping(value = "api/brand")
@CrossOrigin
public class ProductBrandController {
	@Autowired
	ProductBrandServiceImpl brandService;
	@PostMapping(value = "saveBrand")
	public String savebrand(@RequestBody ProductBrand brand) {
		return brandService.savebrand(brand);
	}
	@GetMapping(value = "showBrand")
	public List<ProductBrand> showbrand(){
		return brandService.showbrand();
	}
	@PostMapping(value = "perticularBrand/{id}")
	@Transactional
	public List<Product> showProductsOfBrand(@PathVariable long id) {
		return brandService.findById(id).getProducts();
	}
	@GetMapping(value = "getDistinctBrand")
	public String getDistinctBrand() {
		return brandService.distinctBrand();	
	}
	@GetMapping(value = "loadDistinctBrand")
	public List<String> loadDistinctBrand(){
		return this.brandService.loadDistinctBrand();
	}
	@PostMapping(value = "updateBrand")
	public String updateBrand(@RequestBody ProductBrand brand) {
		return this.brandService.updateBrand(brand);
	}
	@DeleteMapping(value = "deleteBrand/{id}")
	public String deleteBrand(@PathVariable("id") long id) {
		return this.brandService.deleteBrand(id);
	}
}
