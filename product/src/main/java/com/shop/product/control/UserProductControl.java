package com.shop.product.control;

import java.util.List;


import com.shop.product.exception.ProductNotFound;
import com.shop.product.model.Product;
import com.shop.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("product/user")

public class UserProductControl {

    @Autowired
	private ProductService productService;

	// Product get all
	@GetMapping("/getall")
	public List<Product> getAllProducts() {

		return productService.getAll();
	}

	// Product get by name
	@GetMapping("/getproname/{productName}")
	public List<Product> getProductByName(
			@PathVariable("productName") String productName)
			throws Exception {

		List<Product> products = productService.getByProductName(productName);
		if (products.isEmpty())
			throw new ProductNotFound("The product name => " +productName + " not found");

		else {
			return products;
		}
	}


	// Product get by type
	@GetMapping("getprotype/{productType}")
	public List<Product> getProductByType(
		 @PathVariable("productType") String productType)
			throws Exception {

		List<Product> products = productService.getByProductType(productType);
		if (products.isEmpty())
			throw new ProductNotFound("The prodcuttype=> " + productType + " not found");

		else {
			return products;
		}
	}

	// Product get by Id
	@GetMapping("/getproid/{productId}")
	public ResponseEntity<Product> getProductById(
			@PathVariable("productId") String productId) throws Exception {
		Product productData = productService.getById(productId);

		if (productData == null) {
			throw new ProductNotFound("The searched product id => " + productId + " not found");
		}
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}
    
}
