package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {
	@Query("select user from User user where user.username=:username")
	public User existByUsername(@RequestParam("user") String username);
	
	@Query("select user from User user where user.username=:username and user.password=:password")
	public User findUserByUserNameAndPassword(@RequestParam("username") String username,@RequestParam("password") String password);
	@Query("select user from User user where user.username=:username")
	public User findUserByUserUsername(@RequestParam("username") String username);
}
