package com.ecom.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductReviews {
	@Id
	@GeneratedValue
	private long id;
	private String description;
	private String url;
	private int rating;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime createAt;
	@ManyToOne
	@JsonIgnore
	private Product product;
	@Override
	public String toString() {
		return "ProductReviews [id=" + id + ", description=" + description + ", url=" + url + ", rating=" + rating
				+ ", createdAt=" + createdAt + ", createAt=" + createAt + "]";
	}
	
}
