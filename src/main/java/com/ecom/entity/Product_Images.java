package com.ecom.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Product_Images {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private long id;
private String image1;
private String image2;
private String image3;
private String image4;
private String image5;
@JsonIgnore
@OneToOne(mappedBy = "images",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
private Product product;
@Override
public String toString() {
	return "Product_Images [id=" + id + ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3 + ", image4="
			+ image4 + ", image5=" + image5 + "]";
}

}
