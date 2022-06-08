package com.ecom.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Builder
@Entity
public class ProductForGender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String gender;
	@CreationTimestamp
	private LocalDateTime creatdAt;
	@UpdateTimestamp
	private LocalDateTime updateAt;
	@JsonIgnore
	@OneToMany(mappedBy = "gender",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Product> products;
	@Override
	public String toString() {
		return "ProductForGender [id=" + id + ", gender=" + gender + ", creatdAt=" + creatdAt + ", updateAt=" + updateAt
				+ "]";
	}
	
}
