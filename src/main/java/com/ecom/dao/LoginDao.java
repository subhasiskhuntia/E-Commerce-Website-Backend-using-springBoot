package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.bean.Login;



@Repository
public interface LoginDao extends JpaRepository<Login, Long> {
	Login findByUsername(String username);

}