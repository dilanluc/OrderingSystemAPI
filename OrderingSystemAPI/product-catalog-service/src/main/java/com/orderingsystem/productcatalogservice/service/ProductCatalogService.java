package com.orderingsystem.productcatalogservice.service;

import com.orderingsystem.productcatalogservice.model.Product;
import com.orderingsystem.productcatalogservice.repository.ProductCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductCatalogRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public void addProduct (Product product) {
        if(repository.existsByName(product.getName())){
            throw new IllegalArgumentException("Product already in catalog.");
        } else {
            repository.save(product);
        }
    }

    public void removeProduct (int id) {
        repository.deleteById(id);
    }

    public int stockInfoByName (String name) {
        Product product = repository.findByName(name);
        return product.getQuantity();
    }

    public int stockInfoByType (String type) {
        List<Product> products = repository.findByType(type);
        List<Integer> quantities = products.stream().map(Product::getQuantity).collect(Collectors.toList());
        return quantities.stream().mapToInt(i -> i).sum();
    }

    public void stockProduct (int id, int quantity) {
        Product product = repository.getReferenceById(id);
        int oldQuantity = product.getQuantity();
        int newQuantity = quantity + oldQuantity;
        if (newQuantity >= 0) {
            product.setQuantity(newQuantity);
            repository.save(product);
        }
    }

    public boolean isInStock(List<String> names, List<Integer> quantities) {
        List<Product> productList = repository.findByNameIn(names);
        int i = 0;
        boolean inStock = false;
        for(Product product : productList){
            if(product.getQuantity() >= quantities.get(i)){
                inStock = true;
            } else {
                inStock = false;
                break;
            }
        }
        return inStock;
    }

    public void updateStock(List<String> names, List<Integer> quantities){
        List<Product> productList = repository.findByNameIn(names);
        int i = 0;
        for(Product product : productList){
            stockProduct(product.getId(), quantities.get(i) * -1);
            i++;
        }
    }
}
