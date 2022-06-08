package com.ecom.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.AdminDao;
import com.ecom.entity.Admin;
import com.ecom.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao dao;
	
	
	public String  saveAdmin(Admin admin) {
		Admin adminExist=dao.checkEmailExistOrNot(admin.getEmail());
		if(adminExist==null) {
			dao.save(admin);
			return "Successfully Signed In";
		}
		System.out.println("can not store");
		return "Email Id already Exist do LogIn";
	}
	public String login(String email,String password) {
		Admin admin=dao.login(email, password);
		if(admin==null)
			return "Invalid Credentials";
		return "Welcome "+admin.getFirstName();
		
	}

}
