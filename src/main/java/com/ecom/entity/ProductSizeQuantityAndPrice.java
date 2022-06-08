package com.ecom.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSizeQuantityAndPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;
	private String size;
	private int quantity;
	private float price;
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Product sizeQuantityAndPrice;
//
	@Override
	public String toString() {
		return "ProductSizeQuantityAndPrice [id=" + pid + ", size=" + size + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
