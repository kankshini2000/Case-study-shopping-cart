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
        return restTemplate.getForObject("http://localhost:9001/product/user/getById/"+productId, Product.class);
    }
    
}
