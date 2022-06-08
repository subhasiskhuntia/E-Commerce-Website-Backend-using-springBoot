package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select p from Product p where trim(lower(p.name)) like :item "
			+ "or trim(lower(p.brand.name)) like :item "
			+ "or trim(lower(p.description)) like :item "
			+ "or trim(lower(p.category.type)) like :item")
	public List<Product> searchItem(@RequestParam("item") String item);	
//	@Query("select p from Product p where p.price> :price")
//	public List<Product> findProductByPrice(@Param("price") float price);
//	
//	@Query("select p from Product p order by p.price asc")
//	public List<Product> sortByPrice();
//	
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query("delete from Product p where p.pname=:pname")
//	public int deleteProductByName(@Param("pname") String pname);
	
	@Query("select p from Product p where p.category.type  in (:item)")
	public List<Product> getProduct(@RequestParam("item") String[] item);
	
	@Query("select distinct(p.color) from Product p order by p.color")
	public List<String> getDistinct();
//	@Transactional
//	@Query("select p from Product p where "
//			+ "("
//			+ "trim(lower(p.name)) like :item "
//			+ "or trim(lower(p.brand.name)) like :item "
//			+ "or trim(lower(p.description)) like :item "
//			+ "or trim(lower(p.category.type)) like :item"
//			+ ") "
//			+ "and "
//			+ "p.brand.name in (:brand) "
//			+ "and (p.category.type in (:category)) "
//			+ "and (p.color in (:color))"
//			+ "and "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ "<:maxPrice)"
//			+ "and "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ ">:minPrice)"
//			+ " order by p.name desc"
////			
//			)
//	public List<Product> getFilteredProduct(
//			@RequestParam("item") String item,
//			@RequestParam("brand") String[] brand,
//			@RequestParam("category") String[] category,
//			@RequestParam("color") String[] color,
//			@RequestParam("maxPrice") float maxPrice,
//			@RequestParam("minPrice") float minPrice
//			);
	@Query("select p from Product p where (select min(psq.price) from ProductSizeQuantityAndPrice psq where psq.sizeQuantityAndPrice=p)>934")
	public List<Product> pricedProduct();
	
	@Query("select distinct(psqp.sizeQuantityAndPrice) from ProductSizeQuantityAndPrice psqp where("
//			+ "select p from Product p where "
//			+ "("
			+ "trim(lower(psqp.sizeQuantityAndPrice.name)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.brand.name)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.description)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.category.type)) like :item"
			+ ") "
			+ "and "
			+ "psqp.sizeQuantityAndPrice.brand.name in (:brand) "
			+ "and (psqp.sizeQuantityAndPrice.category.type in (:category)) "
			+ "and (psqp.sizeQuantityAndPrice.color in (:color))"
			+ "and (psqp.sizeQuantityAndPrice.gender.gender in (:gender)) "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ "<:maxPrice)"
//			+ "and "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ ">:minPrice)"
//			+ ")=psqp.sizeQuantityAndPrice "
			+ "and (psqp.price<=:maxPrice) and (psqp.price>=:minPrice) order by psqp.price desc"
//			
			)
	public List<Product> getFilteredProduct(
			@RequestParam("item") String item,
			@RequestParam("brand") String[] brand,
			@RequestParam("category") String[] category,
			@RequestParam("color") String[] color,
			@RequestParam("maxPrice") float maxPrice,
			@RequestParam("minPrice") float minPrice,
			@RequestParam("gender") String[] gender
			);
	@Query("select distinct(psqp.sizeQuantityAndPrice) from ProductSizeQuantityAndPrice psqp where("
//			+ "select p from Product p where "
//			+ "("
			+ "trim(lower(psqp.sizeQuantityAndPrice.name)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.brand.name)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.description)) like :item "
			+ "or trim(lower(psqp.sizeQuantityAndPrice.category.type)) like :item"
			+ ") "
			+ "and "
			+ "psqp.sizeQuantityAndPrice.brand.name in (:brand) "
			+ "and (psqp.sizeQuantityAndPrice.category.type in (:category)) "
			+ "and (psqp.sizeQuantityAndPrice.color in (:color))"
			+ "and (psqp.sizeQuantityAndPrice.gender.gender in (:gender)) "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ "<:maxPrice)"
//			+ "and "
//			+ "((select min(psq.price) from ProductSizeQuantityAndPrice psq "
//			+ "where psq.sizeQuantityAndPrice=p)"
//			+ ">:minPrice)"
//			+ ")=psqp.sizeQuantityAndPrice "
			+ "and (psqp.price<=:maxPrice) and (psqp.price>=:minPrice) order by psqp.price asc"
//			
			)
	public List<Product> getFilteredProductInAsc(
			@RequestParam("item") String item,
			@RequestParam("brand") String[] brand,
			@RequestParam("category") String[] category,
			@RequestParam("color") String[] color,
			@RequestParam("maxPrice") float maxPrice,
			@RequestParam("minPrice") float minPrice,
			@RequestParam("gender") String[] gender
			);
}
