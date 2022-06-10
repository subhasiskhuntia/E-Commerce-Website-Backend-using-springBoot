package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class UserController {
	@Autowired
	UserServiceImpl userservice;
	
	@PostMapping(value = "signup")
	private String signingUp(@RequestBody User user) {
		System.out.println(user);
		return userservice.signUp(user);
	}
	@PostMapping(value = "login")
	private String login(@RequestBody Login login) {
		System.out.println(login);
		return userservice.checkUserNameAndPassword(login.getUsername(), login.getPassword());
	}
	@PostMapping(value = "addToCart")
	private String addToCart(@RequestBody ShoppingCart cart) {
		System.out.println(cart);
		System.out.println(cart.getUserId());
		return userservice.addToCart(cart);
	}
	@PostMapping(value = "showCart")
	public ShoppingCart showCart(@RequestBody ShoppingCart cart) {
		System.out.println(userservice.showCart(cart.getUserId()).getCartItems());
		return userservice.showCart(cart.getUserId());
	}
}
