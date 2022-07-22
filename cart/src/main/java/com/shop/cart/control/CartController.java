package com.shop.cart.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.validation.Valid;

import com.shop.cart.api.ProductTemplate;
import com.shop.cart.exception.CartNotFound;
import com.shop.cart.model.Cart;
import com.shop.cart.model.Item;
import com.shop.cart.model.Product;
import com.shop.cart.service.CartService;

import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	ProductTemplate productTemplate;

    @Autowired
	private CartService cartService;



	double totalPrice=0;

	@PostMapping(value = "/additem/{cartId}/{productId}")
	public ResponseEntity<Cart> addCart(@Parameter(description = "Enter Cart Id") @PathVariable("cartId") String cartId,
			@Parameter(description = "Enter product Id") @PathVariable("productId") String productId) {
		Product product = productTemplate.getProduct(productId);
		Item item = new Item();
		item.setQuantity(1);
		item.setProduct(product);
		try {
			Cart cart = cartService.getCart(cartId);

			if (cart != null) {
				List<Item> items = cart.getItems();
				for (Item value : items) {
					if (value.getProduct().getProductId().equals(product.getProductId())) {
						return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
					}
				}

				items.add(item);
				cart.setItems(items);
				cart.setTotalPrice(cart.getTotalPrice() + item.getProduct().getPrice() * item.getQuantity());

			} else {
				List<Item> items = new ArrayList<>();
				items.add(item);

				cart = new Cart();
				cart.setCartId(cartId);
				cart.setItems(items);
				cart.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());
			}

			Cart updatedCart = cartService.createCart(cart);

			return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get Cart By User Id
	@GetMapping("/getcart/{cartId}")
	public ResponseEntity<?> getCartByUserId(
			 @PathVariable("cartId") String cartId) throws Exception {
		try {
			Cart cart = this.cartService.getCart(cartId);
			if (cart != null) {
				return new ResponseEntity<>(cart, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Cart is empty", HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new CartNotFound("The cart with  cartid=> " + cartId  + " not found");
		}
	}

	// Update Item in Cart
	@PutMapping("/updateitem/{cartId}/{productId}/{quantity}")
	public ResponseEntity<?> updateItemInCart(
			 @PathVariable("cartId") String cartId,
			@PathVariable("quantity") int quantity,
			 @PathVariable("productId") String productId) {

		Product product = productTemplate.getProduct(productId);
		Cart cart = cartService.getCart(cartId);
		List<Item> items = cart.getItems();

		Item previousItem = new Item();
		double price;
		for (Item value : items) {
			if (value.getProduct().getProductId().equals(product.getProductId())) {

				previousItem.setProduct(value.getProduct());
				previousItem.setQuantity(value.getQuantity());

				value.setQuantity(quantity);

			}
		}

		cart.setItems(items);
		if (previousItem.getQuantity() < quantity) {
			price = cart.getTotalPrice() + ((product.getPrice() * quantity)
					- previousItem.getProduct().getPrice() * previousItem.getQuantity());
			cart.setTotalPrice(price);
		} else {
			price = cart.getTotalPrice() - (previousItem.getProduct().getPrice() * previousItem.getQuantity()
					- product.getPrice() * quantity);
			cart.setTotalPrice(price);
		}

		Cart updatedCart = cartService.updateCart(cart);

		return new ResponseEntity<>(updatedCart, HttpStatus.OK);

	}

	// Delete Item from Cart
	@PostMapping("/deleteitem/{cartId}/{productId}")
	public ResponseEntity<?> deleteItemFromCart(
			 @PathVariable("cartId") String cartId,
			 @PathVariable("productId") String productId) throws Exception {
		try {
			Cart cart = this.cartService.getCart(cartId);
			System.out.println(cart.getItems().size());
			
			List<Item> items = cart.getItems();
			Item item = items.stream().filter(x -> x.getProduct().getProductId().equals(productId)).findAny()
					.orElse(null); //if the product id is same stores in items n then we can remvove it.
			items.removeIf(x -> x.getProduct().getProductId().equals(productId));
System.out.println(item);
			cart.setItems(items);
			cart.setTotalPrice(cart.getTotalPrice() - item.getProduct().getPrice() * item.getQuantity());

			Cart cartAfterDeleted = this.cartService.deleteCart(cart);

			return new ResponseEntity<>(cartAfterDeleted, HttpStatus.OK);
		} catch (Exception e) {
			throw new CartNotFound("The cart with cartid " + cartId +" along with its productid " + productId + " not found");
		}
	}

	// Cart delete all
	@DeleteMapping("/deletecart/{cartId}")
	public ResponseEntity<?> deleteCart (
			 @PathVariable("cartId") String cartId) throws Exception{
		try {
			Cart cart = this.cartService.getCart(cartId);
			cart.setTotalPrice(0);
			List<Item> items = new ArrayList<>();
			cart.setItems(items);
			cartService.updateCart(cart);
			return new ResponseEntity<>(cart, HttpStatus.OK);
		} catch (Exception e) {
			throw new CartNotFound("The cart with  cartid=> " + cartId  + " not found");
		}
	}

}
