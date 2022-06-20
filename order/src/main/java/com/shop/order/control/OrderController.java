package com.shop.order.control;


import java.util.List;


import javax.validation.Valid;

import com.shop.order.exception.OrderNotFound;
import com.shop.order.model.Order;

import com.shop.order.service.OrderService;
import com.shop.order.service.SequenceGenerator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "order-api")
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    
	@Autowired
    private OrderService orderService;

    @Autowired
	private SequenceGenerator service;

    // Order register
	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order) {
		order.setOrderid(service.getSequenceNumber(Order.SEQUENCE_NAME));
		Order  orderData = orderService.getById(order.getOrderid());
		if (orderData == null) {
			Order  order2 = orderService.createOrder(order);
			return new ResponseEntity<Order>(order2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
		}
	}

	// Order update
	@PutMapping("/update/{orderid}")
	public ResponseEntity<Order> updateOrder(@RequestBody  Order order, @PathVariable("orderid") Integer orderid) throws Exception {
		Order orderData = orderService.getById(orderid);
		if (orderData != null) {
			Order order2 = order;
			return new ResponseEntity<>(orderService.createOrder(order2), HttpStatus.OK);
		} else {
			throw new OrderNotFound("The order with " + orderid + " not found ");
		}
	}

	// Order Delete
	@DeleteMapping("/delete/{orderid}")
	public String deleteById( @PathVariable Integer orderid) throws Exception {

		Order orderData = orderService.getById(orderid);
		if (orderData != null) {
			orderService.deleteById(orderid);
			return "Order deleted with orderid :=>" +orderid;
		} else {
			throw new OrderNotFound("The order with " + orderid + " not found ");
		}
	}

	//Order get all
	@GetMapping("/getAll")
	public List<Order> getAllOrders() {

		return orderService.getAll();
	}

	// Order get by status
	@GetMapping("/getByName/{orderStatus}")
	public List<Order>  getByOrderStatus(@PathVariable("orderStatus")  String orderStatus)
			throws Exception {

		List<Order> orders = orderService.getByOrderStatus(orderStatus);
		if (orders.isEmpty())
			throw new OrderNotFound("The orderstatus "+orderStatus + " not found");

		else {
			return orders;
		}
	}


	// Order get by date
	@GetMapping("getByType/{orderDate}")
	public List<Order> getByOrderDate( @PathVariable("productType") String orderDate)
			throws Exception {

		List<Order> orders = orderService.getByOrderDate( orderDate);
		if (orders.isEmpty())
			throw new OrderNotFound("This orderdate " +orderDate + " not found");

		else {
			return orders;
		}
	}

	// order get by ids
	@GetMapping("/getById/{orderid}")
	public ResponseEntity<Order> getOrderById( @PathVariable("orderid") Integer orderid) throws Exception {
		Order orderData = orderService.getById(orderid);

		if (orderData == null) {
			throw new OrderNotFound("The order with " + orderid + " not found ");
		}
		return new ResponseEntity<>(orderData, HttpStatus.OK);
	}


}
