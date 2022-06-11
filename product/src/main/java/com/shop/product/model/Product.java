package com.shop.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    // @Transient
	// public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
	private String id;
	private String productType;
	private String productName;
	private String category;
	private String image;
	private Double price;
	private Double rating;
	private String description;
    public Product(String id, String productType, String productName, String category, String image, Double price,
            Double rating, String description) {
        this.id = id;
        this.productType = productType;
        this.productName = productName;
        this.category = category;
        this.image = image;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }
    public Product(){

    }
    // public static String getSequenceName() {
    //     return SEQUENCE_NAME;
    // }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Product [category=" + category + ", description=" + description + ", id=" + id + ", image=" + image
                + ", price=" + price + ", productName=" + productName + ", productType=" + productType + ", rating="
                + rating + "]";
    }

    
    
}
