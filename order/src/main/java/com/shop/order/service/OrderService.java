package com.shop.order.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.order.model.Order;
import com.shop.order.repo.OrderRepo;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    // create
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}

	// get order by orderId
	public Order getByOrderId(String orderId) {
		return orderRepo.findByOrderId(orderId);
	}

	// get order by customerId
	public List<Order> getByCustomerId(String cartId) {
		return orderRepo.findByCustomerId(cartId);
	}

	// get order by date
	public List<Order> getByDate(LocalDate date) {
		return orderRepo.findByOrderDate(date);
	}

	// update order
	public Order updateOrder(Order order) {
		Order orderData = orderRepo.findByOrderId(order.getOrderId());
		orderData.setOrderStatus(order.getOrderStatus());
		return orderRepo.save(orderData);
	}

	// delete order
	public String deleteOrder(String orderId) {
		Order orderData = orderRepo.findByOrderId(orderId);
		orderRepo.delete(orderData);
		return "order deleted with" +orderId;
	}
}
