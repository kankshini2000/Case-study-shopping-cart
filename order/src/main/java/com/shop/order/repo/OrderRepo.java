package com.shop.order.repo;



import com.shop.order.model.Order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepo extends MongoRepository<Order, String> {

    Order findByorderid(int orderid);

    List<Order> findByOrderStatus(String orderStatus);

    List<Order> findByOrderDate(String orderDate);

	

}