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
		if (brandRepo.findBrandByName(brand.getName()) == null) {
			brandRepo.save(brand);
			return "saved successfully";
		}
		return "Brand name already exist";
	}

	@Transactional
	public List<ProductBrand> showbrand() {
		return brandRepo.findAll();
	}

	public boolean brandExistOrNot(ProductBrand brand) {
		return brandRepo.findBrandByName(brand.getName()) != null;
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

	public List<String> loadDistinctBrand() {

		return this.brandRepo.distinctBrand();
	}

	public String updateBrand(ProductBrand brand) {
		ProductBrand brand2 = this.brandRepo.findById(brand.getId()).orElse(null);
		if (brand2 != null) {
			brand2.setUrl(brand.getUrl());
			this.brandRepo.save(brand2);
			return "Brand updated";
		}
		return "Brand not exist";
	}

	public String deleteBrand(Long id) {
		if (this.brandRepo.existsById(id)) {
			this.brandRepo.deleteById(id);
			return "deleted";
		}
		return "not present";
	}
}
