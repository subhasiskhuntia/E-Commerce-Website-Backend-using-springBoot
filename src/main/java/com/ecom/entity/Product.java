package com.ecom.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String sku;
	private String color;
//	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Product_Images images;
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdAt;
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime updatedAt;
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private ProductForGender gender;
	@JsonIgnore
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<ProductReviews> reviews=new ArrayList<>();
//	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private ProductCategory category;
//	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private ProductDiscount discount;
//	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private ProductBrand brand;
//	@JsonIgnore
	@OneToMany(mappedBy = "sizeQuantityAndPrice",cascade = CascadeType.ALL)
	private List<ProductSizeQuantityAndPrice> sizeAndQuantity;
//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", sku=" + sku + ", color="
//				+ color + ", images=" + images + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", gender="
//				+ gender + ", reviews=" + reviews + ", category=" + category + ", discount=" + discount
//				+ ", sizeAndQuantity=" + sizeAndQuantity + "]";
//	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", sku=" + sku + ", color="
				+ color + ", images=" + images + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
