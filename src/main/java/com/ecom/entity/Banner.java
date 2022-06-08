package com.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Banner {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String url;
public Banner(String url) {
	super();
	this.url = url;
}

}
