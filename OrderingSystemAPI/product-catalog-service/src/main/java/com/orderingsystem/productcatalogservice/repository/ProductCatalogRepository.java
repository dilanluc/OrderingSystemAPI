package com.orderingsystem.productcatalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderingsystem.productcatalogservice.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCatalogRepository extends JpaRepository<Product, Integer>{
	Product findByName (String productName);
	List<Product> findByType (String productType);
	List<Product> findByNameIn (List<String> productName);
	Boolean existsByName (String productName);
}
