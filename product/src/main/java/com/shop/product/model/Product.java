package com.shop.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Transient
	public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
	private String productId;
    @NotNull(message = "productType shouldnot be null")
	private String productType;

    @NotNull(message = "productName shouldnot be null")
	private String productName;

    @NotNull(message = "caegory shouldnot be null")
	private String category;
    
    @NotNull(message = "image shouldnot be null")
	private String image;

    @NotNull(message = "price shouldnot be null")
	private Double price;

    @NotNull(message = "rating shouldnot be null")
	private Double rating;

    @NotNull(message = "description shouldnot be null")
	private String description;
    public Product(){

    }
    public Product(String productId, String productType,
     String productName,
    String category,
     String image,
     Double price,
     Double rating,
    String description) {
        this.productId = productId;
        this.productType = productType;
        this.productName = productName;
        this.category = category;
        this.image = image;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    

    
    
}
