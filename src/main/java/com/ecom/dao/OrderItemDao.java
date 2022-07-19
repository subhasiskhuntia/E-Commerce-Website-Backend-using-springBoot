package com.ecom.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Query(value ="select product.id,product.name, CONVERT(product_images.image1  USING utf8) as image,sum(order_item.quantity) as sales ,avg(product_size_quantity_and_price.price) as price "
			+ " from product_size_quantity_and_price "
			+ "inner join product on product.id= product_size_quantity_and_price.size_quantity_and_price_id "
			+ "inner join product_images on product.images_id=product_images.id "
			+ "inner join order_item on order_item.product_id=product.id "
			+ "where convert(order_item.updated_at,date)<=:till and "
			+ " convert(order_item.updated_at,date)>=:from "
			+ "group by order_item.product_id "
			+ "order by product.id"
			+ "",nativeQuery = true )
	public List<Map<String, Object>> getSalesData(@RequestParam("from") LocalDate from, @RequestParam("till") LocalDate till);

	

}
