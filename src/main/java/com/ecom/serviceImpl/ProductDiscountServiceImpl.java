package com.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ProductDiscountRepo;
import com.ecom.entity.ProductDiscount;

@Service
public class ProductDiscountServiceImpl {
	@Autowired
	ProductDiscountRepo discountRepo;
	public boolean discountExistOrNot(ProductDiscount discount) {
		return discountRepo.findByDiscountByAmount(discount.getDiscount_percent())!=null;
	}
	public ProductDiscount findDiscountByAmount(ProductDiscount discount) {
		return discountRepo.findByDiscountByAmount(discount.getDiscount_percent());
	}
}
