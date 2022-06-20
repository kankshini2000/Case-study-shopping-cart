package com.shop.order.model;



public class Cart {

	private String cartId;
	private double totalPrice;
	private Item items;
    public Cart(){

    }
    public Cart(String cartId, double totalPrice, Item items) {
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
    public Item getItems() {
        return items;
    }
    public void setItems(Item items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", items=" + items + ", totalPrice=" + totalPrice + "]";
    }
    
    
    
    
    
}
