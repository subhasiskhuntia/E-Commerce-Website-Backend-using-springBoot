package com.ecom.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ProductDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private float discount_percent;
	@Column(name = "activate",columnDefinition = "boolean default 1")
	private boolean activate;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private LocalDateTime deleteAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "discount" ,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Product> products;
	@Override
	public String toString() {
		return "ProductDiscount [id=" + id + ", name=" + name + ", description=" + description + ", discount_percent="
				+ discount_percent + ", activate=" + activate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deleteAt=" + deleteAt + "]";
	}
	
}
