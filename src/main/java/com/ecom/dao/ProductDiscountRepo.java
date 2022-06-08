package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.ProductDiscount;

public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Long> {
	@Query("select discount from ProductDiscount discount where discount.discount_percent=:discount")
	public ProductDiscount findByDiscountByAmount(@RequestParam("discount") float discount);
}
