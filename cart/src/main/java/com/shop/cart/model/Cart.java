package com.shop.cart.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "cart")
public class Cart {

    @Transient
	public static final String SEQUENCE_NAME = "users_sequence";

    
    @Id
	private int cartid;
    @NotNull(message="Total price should be mandtory")
	private double totalPrice;
    @NotBlank(message="item should be mandatory")
	private List<Item> items;
    public Cart(){
        super();
    }
    public Cart(int cartid, double totalPrice, List<Item> items) {
        super();
        this.cartid = cartid;
        this.totalPrice = totalPrice;
        this.items = items;
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
        return "Cart [cartid=" + cartid + ", items=" + items + ", totalPrice=" + totalPrice + "]";
    }
    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }
    public int getCartid() {
        return cartid;
    }
    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    
}
