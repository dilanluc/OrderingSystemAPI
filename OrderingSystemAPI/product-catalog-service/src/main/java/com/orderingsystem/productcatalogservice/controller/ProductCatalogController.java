package com.orderingsystem.productcatalogservice.controller;

import com.orderingsystem.productcatalogservice.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.orderingsystem.productcatalogservice.model.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductCatalogController {
	@Autowired
	private final ProductCatalogService service;

	@GetMapping("/getAllProducts")
	@ResponseStatus (HttpStatus.OK)
	public List<Product> findAll() { return service.findAll(); }
	
	@PostMapping("/addProducts")
	@ResponseStatus (HttpStatus.CREATED)
	public void addProduct (@RequestBody Product product) {
		service.addProduct(product);
	}
	
	@DeleteMapping("/{id}")	  
	@ResponseStatus (HttpStatus.OK) 
	public void removeProduct (@PathVariable("id") int id) {
		service.removeProduct(id);
	}
	
	@GetMapping("/stockInfoName/{name}")
	@ResponseStatus (HttpStatus.OK)
	public int stockInfoByName (@PathVariable("name") String name) {
		return service.stockInfoByName(name);
	}


	@GetMapping("/stockInfoType/{type}")
	@ResponseStatus (HttpStatus.OK)
	public int stockInfoByType (@PathVariable("type") String type) {
		return service.stockInfoByType(type);
	}

	
	@PutMapping("/stockProduct/{id}/{quantity}")
	@ResponseStatus(HttpStatus.OK)
	public void stockProduct (@PathVariable("id") int id, @PathVariable("quantity") int quantity) {
			service.stockProduct(id, quantity);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@RequestParam List<String> name, @RequestParam List<Integer> quantity){
		return service.isInStock(name, quantity);
	}

	@PutMapping ("/updateStock")
	@ResponseStatus(HttpStatus.OK)
	public void updateStock(@RequestParam List<String> name, @RequestParam List<Integer> quantity){
		service.updateStock(name, quantity);
	}
}
