package com.shop.product.repo;

import com.shop.product.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
    
}
