package com.orderingsystem.productcatalogservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.orderingsystem.productcatalogservice.model.Product;
import com.orderingsystem.productcatalogservice.repository.ProductCatalogRepository;

@SpringBootApplication
public class ProductCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner runner (ProductCatalogRepository repository) {
		return args -> {
			repository.save(new Product (1, "CPU", "Intel i7", "a intel cpu", 7));
		};
	}
	*/
}
