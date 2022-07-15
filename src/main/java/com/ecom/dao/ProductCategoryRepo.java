package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.ProductCategory;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>{
	@Query("select category from ProductCategory category where category.type=:type")
	public ProductCategory findCategoryByType(@RequestParam("type") String type);
	@Query("select cat.type from ProductCategory cat order by cat.type")
	public List<String> getDistinctCategoryType();
	
}
