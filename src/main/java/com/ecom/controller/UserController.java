package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.bean.Login;
import com.ecom.entity.ShoppingCart;
import com.ecom.entity.User;
import com.ecom.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",maxAge =3600 )
public class UserController {
	@Autowired
	UserServiceImpl userservice;
	
	@PostMapping(value = "addToCart")
	private String addToCart(@RequestBody ShoppingCart cart) {
		System.out.println(cart);
		System.out.println(cart.getUserId());
		return userservice.addToCart(cart);
	}
	@PostMapping(value = "showCart")
	public ShoppingCart showCart(@RequestBody ShoppingCart cart) {
//		System.out.println(userservice.showCart(cart.getUserId()).getCartItems());
		System.out.println("Inside showcart");
//		return new ShoppingCart();
		return userservice.showCart(cart.getUserName());
	}
}
