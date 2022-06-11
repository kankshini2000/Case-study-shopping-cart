package com.shop.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "cart")
public class Cart {

    @Transient
	public static final String SEQUENCE_NAME = "users_sequence";

    
    @Id
	private int id;
	private double totalPrice;
	private List<Item> items;
    public Cart(){
        super();
    }
    public Cart(int id, double totalPrice, List<Item> items) {
        super();
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
        return "Cart [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + "]";
    }
    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    
}
