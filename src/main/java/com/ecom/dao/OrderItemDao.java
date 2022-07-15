package com.ecom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entity.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long>{
	@Query(value = "select product_category.type as Category,sum(order_item.quantity) as Sales "
			+ "from product inner join order_item "
			+ "on product.id=order_item.product_id "
			+ "inner join product_category "
			+ "on product.category_id=product_category.id "
			+ "group by product.category_id "
			+ "order by product_category.type",
			nativeQuery = true)
	public List<Map<String, Object>> getSalesInCategory();
	
	@Query(value = "select product_brand.name as Brand,sum(order_item.quantity) as Sales "
			+ "from product inner join order_item "
			+ "on product.id=order_item.product_id "
			+ "inner join product_brand "
			+ "on product.brand_id=product_brand.id "
			+ "group by product_brand.id "
			+ "order by product_brand.name;",
			nativeQuery = true)
	public List<Map<String, Object>> getSalesInBrands();

}
