package com.ecom.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.CartDao;
import com.ecom.dao.CartItemDao;
import com.ecom.dao.OrderItemDao;
import com.ecom.dao.ProductRepository;
import com.ecom.dao.UserDAO;
import com.ecom.entity.CartItem;
import com.ecom.entity.OrderDetails;
import com.ecom.entity.Product;
import com.ecom.entity.ShoppingCart;
import com.ecom.entity.User;

@Service
public class UserServiceImpl {
	@Autowired
	UserDAO userdao;
	@Autowired
	CartItemDao cartitemdao;
	@Autowired
	CartDao cartdao;
	@Autowired
	OrderItemDao orderItemdao;
	@Autowired
	ProductRepository productDao;

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
		if (cartitemdao.getProducts(cart.getCartItems().get(0).getCartProduct(),cart.getUserName()).size() !=0 ) {
//			System.out.println(cartitemdao.getProducts(cart.getCartItems().get(0).getCartProduct(),cart.getUserName()));
			return "Already in your Cart";
		}
		User user = userdao.findByUsername(cart.getUserName());
		user.getCart().getCartItems().add(cart.getCartItems().get(0));
		user.getCart().setUser(user);
		user.getCart().getCartItems().forEach(theCart->theCart.setBelongsToThisCart(user.getCart()));
		System.out.println(user.getCart().getCartItems()+"cart items");
		userdao.saveAndFlush(user);
		
		return "Added to Cart";
	}
	public ShoppingCart showCart(String userName) {
		User user=userdao.findUserByUserUsername(userName);
		if(user!=null) {
			return user.getCart();
		}
		return null;
	}
	
	public ShoppingCart updateCart(ShoppingCart cart) {
//		System.out.println("update cart");
//		System.out.println(cart);
		ShoppingCart cart2=cartdao.findById(cart.getId()).orElse(null);
		if (cart2!=null) {
			cart.getCartItems().forEach(theCart->theCart.setBelongsToThisCart(cart2));
			cart2.setCartItems(cart.getCartItems());
			cartdao.saveAndFlush(cart2);
			return cart;
		}
		return null;
		
	}
	public ShoppingCart deleteCartItem(ShoppingCart cart) {
		CartItem cartItem=cart.getCartItems().get(0);
		Optional<CartItem> item=cartitemdao.findById(cartItem.getId());
		System.out.println(item.get());
		if (item.isPresent()) {
			System.out.println("Deleted if block");
			cartitemdao.delete(cartItem);
//			item.get().getBelongsToThisCart().getCartItems().remove(item);
			System.out.println(cartItem.getBelongsToThisCart());
//			
		}
		return null;
	}
	public String saveOrder(OrderDetails orderDetails) {
		User user=userdao.findByUsername(orderDetails.getUsername());
		orderDetails.setOrder_user(user);
//		orderDetails.getOrderItem().forEach(a->a.setOrderDetails(orderDetails));
		List<OrderDetails> orderDetailList=new ArrayList<>();
		orderDetailList.add(orderDetails);
		user.setOrderDetails(orderDetailList);
		List<Product> productList=new ArrayList<>();
		orderDetails.getOrderItem().forEach(order->{
			order.setOrderDetails(orderDetails);
			Product product=productDao.findById(order.getProduct().getId()).orElse(null);
			if(product.getSizeAndQuantity().get(0).getQuantity()<=order.getQuantity()) {
				order.setQuantity(product.getSizeAndQuantity().get(0).getQuantity());
				product.getSizeAndQuantity().get(0).setQuantity(0);				
			}
			else {
				product.getSizeAndQuantity().get(0).setQuantity(order.getProduct().getSizeAndQuantity().get(0).getQuantity()-order.getQuantity());
			}
			productList.add(product);
		});
//		user.getOrderDetails().get(0).getOrderItem().forEach(a->System.out.println(a.getProduct().getName()));
		userdao.saveAndFlush(user);
		productDao.saveAllAndFlush(productList);
//		orderItemdao.saveAll(orderDetails.getOrderItem());
		return "saved";
	}
	public String deleteCartItemFromCart(ShoppingCart cart) {
		cartitemdao.deleteCartItem(cart);
		return "Deleted";
	}

	public List<OrderDetails> getAllOrderDetails(String username) {
		User user=userdao.findByUsername(username);
		if(user!=null) {
			System.out.println(user.getOrderDetails());
			return user.getOrderDetails();
		}
		return null;
	}

	public User getUserDetails(String username) {
		return userdao.findByUsername(username);
	}

	public String changeUserDetails(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user2=userdao.findByUsername(user.getUsername());
		if(user2==null) {
			return "Invalid username";
		}
		if(passwordEncoder.matches(user.getPassword(), user2.getPassword())) {
			user2.setAddress(user.getAddress());
			user2.setPhoneNumber(user.getPhoneNumber());
			user2.setFirst_name(user.getFirst_name());
			user2.setLast_name(user.getLast_name());
			userdao.save(user2);
			return "User details changed";
		}
		else {
			return "Incorrect password";
		}
	}

	public String changePassword(String username, String oldPassword, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user2=userdao.findByUsername(username);
		if(user2==null) {
			return "Invalid username";
		}
		if(passwordEncoder.matches(oldPassword, user2.getPassword())) {
			user2.setPassword(passwordEncoder.encode(newPassword));
			userdao.save(user2);
			return "Password changed";
		}
		else {
			return "Incorrect password";
		}
	
	}
}
