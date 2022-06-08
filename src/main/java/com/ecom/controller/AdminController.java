package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.bean.Login;
import com.ecom.entity.Admin;
import com.ecom.service.AdminService;
import com.ecom.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("signin")
	public String saveAdmin(@RequestBody Admin admin) {
//		Admin admin=Admin.builder().email(email).firstName(firstName).lastName(lastName).password(password).phone(phone).address(address).build();
		System.out.println(admin);
		return adminService.saveAdmin(admin);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "login")
	public String login(@RequestBody Login login) {
		System.out.println("in the login");
		return adminService.login(login.getUsername(),login.getPassword());
	}
}
