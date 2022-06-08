package com.ecom.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.ProductBrandRepo;
import com.ecom.entity.ProductBrand;

@Service
public class ProductBrandServiceImpl {
	@Autowired
	private ProductBrandRepo brandRepo;
	public String savebrand(ProductBrand brand) {
		System.out.println(brand.getId());
		if (!brandRepo.existsById(brand.getId())) {
			brandRepo.save(brand);
			return "saved successfully";
		}
		return "unable to save";
	}
	@Transactional
	public List<ProductBrand> showbrand(){
		return brandRepo.findAll();
	}
	public boolean brandExistOrNot(ProductBrand brand) {
		return brandRepo.findBrandByName(brand.getName())!=null;
	}
	public ProductBrand findBrandByName(ProductBrand brand) {
		return brandRepo.findBrandByName(brand.getName());
	}
	@Transactional
	public ProductBrand findById(long id) {
		return brandRepo.findById(id).orElse(null);
	}
	public String distinctBrand() {
		return String.valueOf(brandRepo.distinctBrand());
	}
}
