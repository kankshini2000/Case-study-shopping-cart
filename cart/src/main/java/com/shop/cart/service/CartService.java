package com.shop.cart.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.cart.model.Cart;
import com.shop.cart.repo.CartRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

   // create orders
	public Cart createCart( Cart cart) {
		return cartRepo.save(new Cart(cart.getCartid(),cart.getTotalPrice(),cart.getItems()));
	}

	// findAll orders
	public List< Cart> getAll() {
		return cartRepo.findAll();
	}

	// find orders by IDs
	public Cart getById(int cartid) {
		return cartRepo.findBycartid(cartid);
	}

	// update orders
	public  Cart updateCart( Cart cart) {
		Cart cart2 = cartRepo.findBycartid(cart.getCartid());
        cart2.setCartid(cart.getCartid());
        cart2.setTotalPrice(cart.getTotalPrice());
        cart2.setItems(cart.getItems());

		return cartRepo.save(cart2);
	}

	// delete product by productid
	public void deleteById(int cartid) {
		Cart cart= cartRepo.findBycartid( cartid);
		cartRepo.delete(cart);
	}
}
