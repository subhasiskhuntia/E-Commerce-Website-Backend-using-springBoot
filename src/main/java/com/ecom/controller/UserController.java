package com.ecom.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.bean.Login;
import com.ecom.entity.CartItem;
import com.ecom.entity.OrderDetails;
import com.ecom.entity.ShoppingCart;
import com.ecom.entity.User;
import com.ecom.serviceImpl.OtpServiceImpl;
import com.ecom.serviceImpl.UserServiceImpl;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",maxAge =3600 )
public class UserController {
	@Autowired
	UserServiceImpl userservice;
	@Autowired
	OtpServiceImpl otpService;
	
	@PostMapping(value = "addToCart")
	private String addToCart(@RequestBody ShoppingCart cart) {
		System.out.println(cart);
		return userservice.addToCart(cart);
	}
	
	@PostMapping(value = "showCart")
	public ShoppingCart showCart(@RequestBody ShoppingCart cart) {
//		System.out.println(userservice.showCart(cart.getUserId()).getCartItems());
//		System.out.println("Inside showcart");
//		return new ShoppingCart();
		return userservice.showCart(cart.getUserName());
	}
	@PostMapping(value = "updateCart")
	public ShoppingCart updateCart(@RequestBody ShoppingCart cart) {
		return this.userservice.updateCart(cart);
	}
	@PostMapping(value = "/deleteCartItem",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ShoppingCart deleteCartItem(@RequestBody ShoppingCart cart) {
		System.out.println("inside delete cart controller");
//		System.out.println(userservice.deleteCartItem(cart.getCartItems().get(0)));
		System.out.println(cart);
		return userservice.deleteCartItem(cart);
		
	}
	
	@PostMapping(value = "/buyProduct")
	public String buyProduct(@RequestBody Map<String, Object> data) throws Exception {
//		System.out.println(data);
		int amount=Integer.parseInt(data.get("amount").toString());
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_57H5LdGcUCbfNi", "1YAHwxJD9blFaaLBAr0h9az7");
		JSONObject options = new JSONObject();
		options.put("amount", amount*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.orders.create(options);
//		System.out.println(order);
		return order.toString();
	}
	
	@PostMapping(value = "saveRecord")
	public String saveRecord(@RequestBody OrderDetails orderDetails) {
//		System.out.println(orderDetails);
		return userservice.saveOrder(orderDetails);
	}
	
	@PostMapping(value = "deleteAllCartItem")
	public String deleteAllCartItem(@RequestBody ShoppingCart cart) {
		System.out.println(cart);
		return this.userservice.deleteCartItemFromCart(cart);
//		return null;
	}
	@PostMapping(value = "/getOrderDetails")
	public List<OrderDetails> getAllOrderDetails(@RequestBody Map<String, String> usernameMap){
		String username=usernameMap.get("username");
		return this.userservice.getAllOrderDetails(username);
	}
	@PostMapping(value = "getUserDetails")
	public User getUserDetails(@RequestBody Map<String, Object> userNameMap) {
		String username=(String) userNameMap.get("username");
		return this.userservice.getUserDetails(username);
	}
	@PostMapping(value = "changeUserDetails")
	public String changeUserDetails(@RequestBody User user) {
		return this.userservice.changeUserDetails(user);
	}
	@PostMapping(value = "changePassword")
	public String changePassword(@RequestBody Map<String, String> usernameAndPasswordsMap ) {
		String username=usernameAndPasswordsMap.get("username");
		String oldPassword=usernameAndPasswordsMap.get("oldPassword");
		String newPassword=usernameAndPasswordsMap.get("newPassword");
		return this.userservice.changePassword(username,oldPassword,newPassword);
	}
	@PostMapping(value = "checkOtp")
	public String checkOtp(@RequestBody Map<String, String> userNameOtpMap) {
		String username=userNameOtpMap.get("username");
		String otp=userNameOtpMap.get("otp");
		return otpService.checkOtp(username,otp);
	}
}
