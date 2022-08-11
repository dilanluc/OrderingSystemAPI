package com.orderingsystem.productcatalogservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String type;
	private String name;
	private String description;
	private int quantity;
	
	@Override
	public String toString() {
		return "Product [type=" + type + ", name=" + name + ", description=" + description + "]";
	}
	
	
		
}
