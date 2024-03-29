package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.CartItem;
import com.ecom.entity.Product;
import com.ecom.entity.ShoppingCart;

public interface CartItemDao extends JpaRepository<CartItem,Long>{
	@Query("select item.cartProduct from CartItem item where item.cartProduct=:product and item.belongsToThisCart.user.username=:username")
	public List<Product> getProducts(@RequestParam("product") Product product,@RequestParam("username") String username);
	
	@Transactional
	@Modifying
	@Query("delete from CartItem item where item.belongsToThisCart=:cart")
	public void deleteCartItem(@RequestParam("cart") ShoppingCart cart);
}
