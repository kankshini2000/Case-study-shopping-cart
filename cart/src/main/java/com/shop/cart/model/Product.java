package com.shop.cart.model;

public class Product {
    private String productId;
	private String productName;
	private double price;
    
    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product [price=" + price + ", productId=" + productId + ", productName=" + productName + "]";
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}