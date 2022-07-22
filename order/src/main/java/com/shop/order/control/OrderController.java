package com.shop.order.control;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.shop.order.api.CartTemplate;
import com.shop.order.api.UserTemplate;

import com.shop.order.model.Cart;
import com.shop.order.model.Order;
import com.shop.order.model.User;
import com.shop.order.service.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    
	@Autowired
    private OrderService orderService;

	@Autowired
	private UserTemplate userTemplate;

	@Autowired
	private CartTemplate cartTemplate;

   // Order create
	@PostMapping("/addOrder/{cartId}")
	public Order createOrder(
			 @PathVariable("cartId") String cartId) {
		User user = userTemplate.getUserInfo(cartId);
		System.out.println(user.getFullName());
		Cart cart = cartTemplate.getCart(cartId);
		return orderService.createOrder(new Order( cartId, LocalDate.now(), cart.getTotalPrice(), "Order Failed",
				user.getAddress(),user.getMobile_no(), cart.getItems()));
	}

	// Order get all
	@GetMapping("/getOrder/{cartId}")
	public List<Order> getAllOrders(
		@PathVariable("cartId") String cartId) {
		List<Order> list = orderService.getByCustomerId(cartId);
		List<Order> list2 = new ArrayList<>();
		list.stream().sorted((p, q) -> q.getOrderDate().compareTo(p.getOrderDate())).forEach(p -> list2.add(p));
		return list2;
	}

	// order get by current date
	@GetMapping("/getTodaysOrders/{cartId}")
	public List<Order> getTodaysOrders(
		@PathVariable("cartId") String cartId) {
		List<Order> list = orderService.getByCustomerId(cartId);
		List<Order> newList = new ArrayList<>();
		for (Order dateOrder : list) {
			if (dateOrder.getOrderDate().isEqual(LocalDate.now())) {
				newList.add(dateOrder);
			}
		}
		return newList;
	}

	// order get by orderId
	@GetMapping("/getByOrderId/{orderId}")
	public Order getOrder( @PathVariable("orderId") String orderId) {
		return orderService.getByOrderId(orderId);
	}

	// order Update by orderId
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<?> updateOrder(
			 @PathVariable("orderId") String orderId) {
		Order order = orderService.getByOrderId(orderId);
		order.setOrderStatus("Order Placed");
		Order order2 = orderService.createOrder(order);
		return new ResponseEntity<>(order2, HttpStatus.CREATED);
	}

	// order delete by customerId
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(
			@PathVariable("orderId") String orderId) {
		orderService.deleteOrder(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
