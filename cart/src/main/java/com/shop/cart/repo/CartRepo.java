package com.shop.cart.repo;

import com.shop.cart.model.Cart;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {


    Cart findBycartid(int cartid);
	
}
