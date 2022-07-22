package com.shop.order.repo;



import com.shop.order.model.Order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepo extends MongoRepository<Order, String> {

    Order findByOrderId(String orderId);

    List<Order> findByOrderDate(LocalDate date);

    List<Order> findByCustomerId(String cartId);

   

	

}