package com.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ProductGenderRepo;
import com.ecom.entity.ProductForGender;

@Service
public class ProductGenderServiceImpl {
	@Autowired
	ProductGenderRepo genderRepo;
	public boolean genderExistOrNot(ProductForGender gender) {
		return genderRepo.findGenderByName(gender.getGender())!=null;
	}
	public ProductForGender findGenderByName(ProductForGender gender) {
		return genderRepo.findGenderByName(gender.getGender());
	}
	public ProductForGender findGenderByid(int id) {
		return genderRepo.findById(id).orElse(null);
	}
}
