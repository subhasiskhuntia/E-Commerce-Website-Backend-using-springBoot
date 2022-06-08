package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.ProductForGender;

public interface ProductGenderRepo extends JpaRepository<ProductForGender, Integer>{
	@Query("select gender from ProductForGender gender where gender.gender=:gender")
	public ProductForGender findGenderByName(@RequestParam("gender") String gender);

}
