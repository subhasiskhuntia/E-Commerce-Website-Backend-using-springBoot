package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entity.ShoppingCart;

@Repository
public interface CartDao extends JpaRepository<ShoppingCart, Long>{
	
}
