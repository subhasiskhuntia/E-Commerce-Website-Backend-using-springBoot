package com.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.ProductCategoryRepo;
import com.ecom.entity.ProductCategory;

@Service
public class ProductCategoryServiceImpl {
	@Autowired
	ProductCategoryRepo categoryRepo;

	public String saveCategory(ProductCategory category) {
		System.out.println(category.getId());
		if (categoryRepo.findCategoryByType(category.getType())==null) {
			categoryRepo.save(category);
			return "saved successfully";
		}
		return "Category already present";
	}
	@Transactional
	public List<ProductCategory> showCategory(){
		return categoryRepo.findAll();
	}
	public boolean categoryExistOrNot(ProductCategory category){
		if(categoryRepo.findCategoryByType(category.getType())!=null) {
			return true;
		}
		return false;
	}
	public ProductCategory findCategoryByType(ProductCategory category) {
		return categoryRepo.findCategoryByType(category.getType());
	}
	public ProductCategory findCategoryById(long id) {
		return categoryRepo.findById(id).orElse(null);
	}
	public String getDistinctCategory() {
		return String.valueOf(categoryRepo.getDistinctCategoryType());
	}
	public List<String> loadDistinctCategory() {
		List<String> categories= categoryRepo.getDistinctCategoryType();
		return categories;
	}
	public String updateCategory(ProductCategory category) {
		ProductCategory category2=categoryRepo.findById(category.getId()).orElse(null);
		if(category!=null) {
			category2.setUrl(category.getUrl());
			this.categoryRepo.save(category2);
			return "Category updated";
		}
		return null;
	}
	public String deleteCategory(long id) {
		if(this.categoryRepo.existsById(id)) {
			this.categoryRepo.deleteById(id);
			return "Category deleted";
		}
		return "category not exist";
	}
}
