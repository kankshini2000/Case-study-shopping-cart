package com.shop.order.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cart")
public class Cart {

	private String cartId;
	private double totalPrice;
	private List<Item> items;
    public Cart(String cartId, double totalPrice, List<Item> items) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.items = items;
    }
    public Cart(){

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
