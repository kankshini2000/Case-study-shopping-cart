package com.shop.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shop.order.model.Cart;

@Service
public class CartTemplate {
    
    @Autowired
    private RestTemplate restTemplate;

    public Cart getCart(String cartId) {
		return restTemplate.getForObject("http://localhost:9006/cart/getcart/" + cartId, Cart.class);
	}

}
