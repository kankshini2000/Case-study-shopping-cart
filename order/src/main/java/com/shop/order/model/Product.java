package com.shop.order.model;

public class Product {

    private int id;
	private String productName;

	public Product(int id, String productName) {
        this.id = id;
        this.productName = productName;
    }
    public Product(){

    }
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + "]";
    }
    
}
