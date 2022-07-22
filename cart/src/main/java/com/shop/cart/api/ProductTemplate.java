package com.shop.cart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shop.cart.model.Product;

@Service
public class ProductTemplate {

    @Autowired
	private RestTemplate restTemplate;

    public Product getProduct(String productId){
        return restTemplate.getForObject("http://localhost:9009/product/user/getproid/"+productId, Product.class);
    }
    
}
