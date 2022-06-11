package com.shop.order.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shop.order.model.Order;
import com.shop.order.repo.OrderRepo;
import com.shop.order.service.SequenceGenerator;

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
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    
	@Autowired
    private OrderRepo repo;

    @Autowired
	private SequenceGenerator service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Order order){
        order.setId(service.getSequenceNumber(Order.SEQUENCE_NAME));
        repo.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Order> getUser(){
        return repo.findAll();
    }

    @GetMapping("/all/{orderId}")
    public Optional<Order> getUserbyName(@PathVariable String orderId){
        return repo.findById(orderId);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<String> update(@PathVariable int orderId,@RequestBody Order order){
        repo.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/del/{orderId}")
    public String DelUserbyID(@PathVariable String orderId){
        repo.findById(orderId);
        return "Order deleted with orderid => " +orderId;
    }

}
