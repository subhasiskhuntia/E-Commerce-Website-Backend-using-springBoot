package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long>{

}
