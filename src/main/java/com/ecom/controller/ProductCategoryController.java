package com.ecom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Product;
import com.ecom.entity.ProductCategory;
import com.ecom.serviceImpl.ProductCategoryServiceImpl;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class ProductCategoryController {
	@Autowired
	ProductCategoryServiceImpl categoryService;

	@PostMapping(value = "saveCategory")
	public String saveCategory(@RequestBody ProductCategory category) {
		return categoryService.saveCategory(category);
	}
	@GetMapping(value = "showCategory")
	public List<ProductCategory> showCategory(){
		return categoryService.showCategory();
	}
	@GetMapping("categoryProduct/{id}")
	public List<Product> categoryProducts(@PathVariable long id){
		return categoryService.findCategoryById(id).getProducts();
	}
	@GetMapping("getDistinctCategory")
	public String getDistinctCategory() {
		return categoryService.getDistinctCategory();
	}
	@GetMapping(value = "loadDistinctCategory")
	public List<String> loadDistinctCategory(){
//		Map<String, List<String>> output=new HashMap<>();
//		output.put("categories", this.categoryService.loadDistinctCategory());
		return this.categoryService.loadDistinctCategory();
	}
	@PostMapping(value = "updateCategory")
	public String updateCategory(@RequestBody ProductCategory category) {
		return this.categoryService.updateCategory(category);
	}
	@DeleteMapping(value = "deleteCategory/{id}")
	public String deleteCategory(@PathVariable("id") long id) {
		return this.categoryService.deleteCategory(id);
	}
}
