package com.shop.order.model;

public class Item {
    private String productName;
	private Double price;
	private int quantity;

    public Item(){

    }
    
	public Item(String productName, Double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    @Override
    public String toString() {
        return "Item [price=" + price + ", productName=" + productName + ", quantity=" + quantity + "]";
    }
}
