package com.ecom.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.bean.Login;
import com.ecom.dao.LoginDao;
import com.ecom.dao.UserDAO;
import com.ecom.entity.ShoppingCart;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private UserDAO userdao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;	
		com.ecom.entity.User user = userdao.findByUsername(username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}
	
	public String save(com.ecom.entity.User user) {
		if (userdao.existByUsername(user.getUsername()) != null) {
			System.out.println("User Already Exist");
			return "User Already Exist";
		}
		ShoppingCart cart=new ShoppingCart();
		user.setCart(cart);
		user.getCart().setUser(user);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userdao.saveAndFlush(user);
		return "Account Created Successfully";
		
	}

}
