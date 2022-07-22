package com.shop.cart.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")
public class Cart {

    @Id
    @NotNull
	private String cartId;
    @NotNull
	private double totalPrice;
    @NotNull
	private List<Item> items;
    public Cart(){
        
    }

    public Cart(String cartId, double totalPrice, List<Item> items) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.items = items;
    }
    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", items=" + items + ", totalPrice=" + totalPrice + "]";
    }

    
}
