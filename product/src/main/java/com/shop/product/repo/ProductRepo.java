package com.shop.product.repo;

import com.shop.product.model.Product;
import java.util.*;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {

    List<Product> findByProductName(String productName);

    List<Product> findByProductType(String productType);

    Product findByProductId(String productId);




}
