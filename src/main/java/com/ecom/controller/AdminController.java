package com.ecom.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.bean.Login;
import com.ecom.entity.Admin;
import com.ecom.service.AdminService;
import com.ecom.serviceImpl.AdminServiceImpl;
import com.ecom.serviceImpl.OrderItemServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	AdminServiceImpl adminService;
	@Autowired
	OrderItemServiceImpl orderitemService;
	
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
	@GetMapping(value = "salesInCategory")
	public List<Map<String, Object>> getSalesInCategory(){
		return this.adminService.getSalesInCategory();
	}
	@GetMapping(value = "salesInBrand")
	public List<Map<String, Object>> getSalesInBrand(){
		return this.adminService.getSalesInBrand();
	}
	@GetMapping(value = "salesFromTill/{from}/{till}")
	public List<Map<String, Object>> salesFromTill(@PathVariable("from") String from ,@PathVariable("till") String till){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate From = LocalDate.parse(from, formatter);
		LocalDate Till=LocalDate.parse(till,formatter);
		return orderitemService.getSalesDate(From,Till);
	}
}
