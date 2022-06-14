package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.CartItem;
import com.ecom.entity.Product;

public interface CartItemDao extends JpaRepository<CartItem,Long>{
	@Query("select item.cartProduct from CartItem item where item.cartProduct=:product and item.belongsToThisCart.user.id=:userId")
	public List<Product> getProducts(@RequestParam("product") Product product,@RequestParam("userId") long userId);
	
}
