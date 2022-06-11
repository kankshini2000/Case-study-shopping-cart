package com.shop.order.repo;



import com.shop.order.model.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepo extends MongoRepository<Order, String> {

	

}