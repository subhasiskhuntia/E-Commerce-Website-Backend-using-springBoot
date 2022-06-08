package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.bean.FilteringVariable;
import com.ecom.entity.Product;
import com.ecom.serviceImpl.ProductService;


@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;

//	@GetMapping(value = "getAllProducts")
//	public List<Product> getAllProducts() {
//		return productService.findAllProduct();
//	}
//
	@PostMapping(value = "storeProduct")
	public String storeProduct(@RequestBody Product product) {
		System.out.println("inside store controller");
		System.out.println(product);
		return productService.storeProduct(product);
//		return "testing it";
	}
	@GetMapping(value = "searchProduct")
	public List<Product> getSearchedProduct(@RequestParam("item") String search){
		
		return productService.searchProduct("%"+search.toLowerCase()+"%");
	}
	@GetMapping(value = "getDistinctColor")
	public String getDistinctColor() {
		return productService.getDistinctColor();
	}
	@PostMapping(value = "getFilterdProduct")
	public List<Product> getFilteredProducts(@RequestBody FilteringVariable variable){
		System.out.println("variables"+variable);
		if (variable.getOrder().equals("asc")) {
			 
			
			return productService.getFilteredProductAsc("%"+variable.getSearchedItem()+"%", variable.getBrand(),variable.getCategory(),variable.getColor(),variable.getMaxPrice(),variable.getMinPrice(),variable.getGender());
		}
		return productService.getFilteredProduct("%"+variable.getSearchedItem()+"%", variable.getBrand(),variable.getCategory(),variable.getColor(),variable.getMaxPrice(),variable.getMinPrice(),variable.getGender());
	}
//
//	@PutMapping(value = "updateProduct")
//	public String updateProduct(@RequestBody Product product) {
//		System.out.println(product);
//		return productService.updateProduct(product);
//	}
//
//	@DeleteMapping(value = "deleteProduct/{id}")
//	public String deleteProduct(@PathVariable int id) {
//		return productService.deleteProduct(id);
//	}
//
	@GetMapping(value = "findProductById/{id}")
	public Product findProductById(@PathVariable long id) {
		return productService.findProductById(id);
	}
//	
//	@GetMapping(value = "findProductByPrice/{price}")
//		return productService.findProductsByPrice(price);
//	public List<Product> findProductByPrice(@PathVariable float price){
//	}
//	@GetMapping(value = "sortByPrice")
//	public List<Product> sortByPrice() {
//		return productService.sortByPrice();
//	}
//	@DeleteMapping(value = "deleteProductByName")
//	public String deleteProductByName(@RequestBody String name) {
//		return productService.deleteProductByName(name);
//	}
}
