package com.ecom.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.CartDao;
import com.ecom.dao.CartItemDao;
import com.ecom.dao.UserDAO;
import com.ecom.entity.CartItem;
import com.ecom.entity.ShoppingCart;
import com.ecom.entity.User;

@Service
public class UserServiceImpl {
	@Autowired
	UserDAO userdao;
	@Autowired
	CartItemDao cartitemdao;

	public String signUp(User user) {
		if (userdao.existByUsername(user.getUsername()) != null) {
			System.out.println("User Already Exist");
			return "User Already Exist";
		}
		ShoppingCart cart=new ShoppingCart();
		user.setCart(cart);
		user.getCart().setUser(user);
		userdao.saveAndFlush(user);
		return "Account Created Successfully";
	}

	public String checkUserNameAndPassword(String username, String password) {
		User user = userdao.findUserByUserNameAndPassword(username, password);
		if (user == null) {
			if (userdao.findUserByUserUsername(username) == null) {
				return "Username doesn't exist signup";
			}
			return "Invalid Credential";
		}
		return "Welcome" + user.getId() + " " + user.getFirst_name();
	}

	public String addToCart(ShoppingCart cart) {
//		System.out.println(cartitemdao.getProducts(cart.getCartItems().get(0).getCartProduct()));
		if (cartitemdao.getProducts(cart.getCartItems().get(0).getCartProduct(),cart.getUserId()).size() !=0 ) {
			System.out.println(cartitemdao.getProducts(cart.getCartItems().get(0).getCartProduct(),cart.getUserId()));
			return "Already in your Cart";
		}
		User user = userdao.findById(cart.getUserId()).get();
		user.getCart().setCartItems(cart.getCartItems());
		user.getCart().setUser(user);
		user.getCart().getCartItems().forEach(theCart->theCart.setBelongsToThisCart(user.getCart()));
		userdao.save(user);
		
		return "Added to Cart";
	}
	public ShoppingCart showCart(long id) {
		Optional<User> user=userdao.findById(id);
		if(user.isPresent()) {
			return user.get().getCart();
		}
		return null;
	}
}
