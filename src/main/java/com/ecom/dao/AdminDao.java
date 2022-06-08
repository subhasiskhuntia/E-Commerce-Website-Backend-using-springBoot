package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	
	@Query("select admin from Admin admin where admin.email=:email")
	public Admin checkEmailExistOrNot(@RequestParam("email") String email);
	@Query("select admin from Admin admin where admin.email=:email and admin.password=:password")
	public Admin login(@RequestParam("email") String email,@RequestParam("password") String password);
}
