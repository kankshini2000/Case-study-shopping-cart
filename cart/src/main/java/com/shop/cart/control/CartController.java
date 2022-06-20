package com.shop.cart.control;

import java.util.List;


import javax.validation.Valid;

import com.shop.cart.exception.CartNotFound;
import com.shop.cart.model.Cart;

import com.shop.cart.service.CartService;
import com.shop.cart.service.SequenceGenerator;

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
@SecurityRequirement(name = "cart-api")
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
	private CartService cartService;
	
	@Autowired
	private SequenceGenerator service;

	// Order register
	@PostMapping("/add")
	public ResponseEntity<Cart> addOrder(@RequestBody @Valid Cart cart) {
		cart.setCartid(service.getSequenceNumber(Cart.SEQUENCE_NAME));
		Cart  cartData = cartService.getById(cart.getCartid());
		if (cartData == null) {
			Cart  cart2 = cartService.createCart(cart);
			return new ResponseEntity<Cart>(cart2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
		}
	}

	// Order update
	@PutMapping("/update/{cartid}")
	public ResponseEntity<Cart> updateOrder(@RequestBody Cart cart, @PathVariable Integer cartid) throws Exception {
		Cart  cartData = cartService.getById(cart.getCartid());
		if (cartData != null) {
			Cart  cart2 = cart;
			return new ResponseEntity<Cart>(cartService.createCart(cart2), HttpStatus.OK);
		} else {
			throw new CartNotFound("The cart with " + cartid + " not found ");
		}
	}

	// Order Delete
	@DeleteMapping("/delete/{cartid}")
	public String deleteById( @PathVariable Integer cartid) throws Exception {
		Cart  cartData = cartService.getById(cartid);
		if (cartData != null) {
			cartService.deleteById(cartid);
			return "Cart deleted with cartid:=>" + cartid;
		} else {
			throw new CartNotFound("The cart with " + cartid + " not found ");
		}
	}

	//Order get all
	@GetMapping("/getAll")
	public List<Cart> getAllOrders() {

		return cartService.getAll();
	}

	

	// order get by ids
	@GetMapping("/getById/{cartid}")
	public ResponseEntity<Cart> getOrderById( @PathVariable Integer cartid) throws Exception {
		Cart cartData = cartService.getById(cartid);

		if (cartData == null) {
			throw new CartNotFound("The cart with " + cartid + " not found ");
		}
		return new ResponseEntity<>(cartData, HttpStatus.OK);
	}


}
