package com.shop.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.order.model.Order;
import com.shop.order.repo.OrderRepo;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    // create orders
	public Order createOrder( Order order) {
		return orderRepo.save(new Order(order.getOrderid(), order.getOrderDate(),order.getCustomerId(),order.getAmountPaid(),order.getModeOfPayment(),order.getOrderStatus(),order.getQuantity(),order.getAddress(),order.getProduct()));
	}

	// findAll orders
	public List< Order> getAll() {
		return orderRepo.findAll();
	}

	// find orders by date
	public List< Order> getByOrderDate(String orderDate) {
		return orderRepo.findByOrderDate(orderDate);
	}

	// find orders by status
	public List< Order> getByOrderStatus(String orderStatus) {
		return orderRepo.findByOrderStatus( orderStatus);
	}

	// find orders by IDs
	public Order getById(int orderid) {
		return orderRepo.findByorderid(orderid);
	}

	// update orders
	public  Order updateOrder( Order order) {
		Order order2 = orderRepo.findByorderid(order.getOrderid());
        order2.setOrderid(order.getOrderid());
        order2.setOrderDate(order.getOrderDate());
        order2.setCustomerId(order.getCustomerId());
        order2.setAmountPaid(order.getAmountPaid());
        order2.setModeOfPayment(order.getModeOfPayment());
        order2.setOrderStatus(order.getOrderStatus());
        order2.setQuantity(order.getQuantity());
        order2.setAddress(order.getAddress());
		order2.setProduct(order.getProduct());

		return orderRepo.save(order2);
	}

	// delete product by productid
	public void deleteById(int orderid) {
		Order order= orderRepo.findByorderid(orderid);
		orderRepo.delete(order);
	}

}
