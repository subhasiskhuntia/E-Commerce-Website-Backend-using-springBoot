package com.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String firstName;
private String lastName;
private String email;
private String password;
private String phone;
private String address;
//public Admin(String firstName, String lastName, String email, String password, String phone, String address) {
//	super();
//	this.firstName = firstName;
//	this.lastName = lastName;
//	this.email = email;
//	this.password = password;
//	this.phone = phone;
//	this.address = address;
//}

}
