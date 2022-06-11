package com.shop.cart.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shop.cart.model.Cart;
import com.shop.cart.repo.CartRepo;
import com.shop.cart.service.SequenceGenerator;

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
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
   
    //private static final String GET_ALL_ORDES_API="http://localhost:9003/order/all";
    @Autowired
	private SequenceGenerator service;


    @Autowired
    private CartRepo repo;

    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Cart cart){
        cart.setId(service.getSequenceNumber(Cart.SEQUENCE_NAME));
        repo.save(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //-------------------------------------------------------------------------------static RestTemplate restTemplate=new RestTemplate();
    
    @GetMapping("/all")
    public List<Cart> getUser(){
        return repo.findAll();
    }

    @GetMapping("/all/{cartId}")
    public Optional<Cart> getUserbyName(@PathVariable String cartId){
        return repo.findById(cartId);
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<String> update(@PathVariable String cartId,@RequestBody Cart cart){
        repo.save(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/del/{cartId}")
    public String DelUserbyName(@PathVariable String cartId){
        repo.findById(cartId);
        return "Cart deleted with cartid :=>" +cartId;
    }

}
