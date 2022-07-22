package com.shop.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shop.order.model.User;

@Service
public class UserTemplate {

    @Autowired
	private RestTemplate restTemplate;

    public User getUserInfo(String customerId) {
		return restTemplate.getForObject("http://localhost:9005/user/" + customerId, User.class);
	}
}
