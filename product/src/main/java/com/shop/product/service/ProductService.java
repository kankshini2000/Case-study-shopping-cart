package com.shop.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.product.model.Product;
import com.shop.product.repo.ProductRepo;

import io.swagger.v3.oas.models.links.Link;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // create products
	public Product createProduct(Product product) {
		return productRepo.save(new Product(product.getProductId(),product.getProductType(),product.getProductName(),product.getCategory(),
        product.getImage(),product.getPrice(),product.getRating(),product.getDescription()));
	}

	// findAll products
	public List<Product> getAll() {
		return productRepo.findAll();
		
	}

	// find products by productname
	public List<Product> getByProductName(String productName) {
		return productRepo.findByProductName( productName);
	}

	// find products by type
	public List<Product> getByProductType(String productType) {
		return productRepo.findByProductType( productType);
	}

	// find products by IDs
	public Product getById(String productId) {
		return productRepo.findByProductId(productId);
	}

	// update user
	public Product updateProduct(Product product) {
		Product product2 = productRepo.findByProductId(product.getProductId());
        product2.setProductId(product.getProductId());
        product2.setProductType(product.getProductType());
        product2.setProductName(product.getProductName());
        product2.setCategory(product.getCategory());
        product2.setImage(product.getImage());
        product2.setPrice(product.getPrice());
        product2.setRating(product.getRating());
        product2.setDescription(product.getDescription());

		return productRepo.save(product2);
	}

	// delete product by productid
	public String deleteById(String productId) {
		Product product = productRepo.findByProductId(productId);
		productRepo.delete(product);
		return "product deleted with " + productId;
	}

    
}
