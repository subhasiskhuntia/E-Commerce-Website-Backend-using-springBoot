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
		if (!categoryRepo.existsById(category.getId())) {
			categoryRepo.save(category);
			return "saved successfully";
		}
		return "unable to save";
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
}
