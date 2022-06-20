package com.shop.product.control;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.shop.product.exception.ProductNotFound;
import com.shop.product.model.Product;
import com.shop.product.repo.ProductRepo;
import com.shop.product.service.ProductService;
import com.shop.product.service.SequenceGenerator;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("product/admin")
@SecurityRequirement(name="product-api")
public class AdminControl {
    
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SequenceGenerator service;

	// Product register
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody  @Valid Product product) {
		product.setProductId(service.getSequenceNumber(Product.SEQUENCE_NAME));
		Product productData = productService.getById(product.getProductId());
		if (productData == null) {
			Product product2 = productService.createProduct(product);
			return new ResponseEntity<Product>(product2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
		}
	}

	// Product update
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("productId") Integer productId) throws Exception {
		Product productData = productService.getById(productId);
		if (productData != null) {
			Product product2 = product;
			return new ResponseEntity<>(productService.createProduct(product2), HttpStatus.OK);
		} else {
			throw new ProductNotFound("The product with " +productId + " not found ");
		}
	}

	// Product Delete
	@DeleteMapping("/delete/{productId}")
	public String deleteById( @PathVariable Integer productId) throws Exception {

		Product productData = productService.getById(productId);
		if (productData != null) {
			productService.deleteById(productId);
			return "Product deleted with cartid :=>" +productId;
		} else {
			throw new ProductNotFound("The product with " +productId + " not found ");
		}
	}

	// Product get all
	@GetMapping("/getAll")
	public List<Product> getAllProducts() {

		return productService.getAll();
	}

	// Product get by name
	@GetMapping("/getByName/{productName}")
	public List<Product> getProductByName(@PathVariable("productName") String productName)
			throws Exception {

		List<Product> products =  productService.getByProductName(productName);
		if (products.isEmpty())
			throw new ProductNotFound("The product with " + productName + " not found");

		else {
			return products;
		}
	}


	// Product get by type
	@GetMapping("getByType/{productType}")
	public List<Product> getProductByType( @PathVariable("productType") String productType)
			throws Exception {

		List<Product> products = productService.getByProductType(productType);
		if (products.isEmpty())
			throw new ProductNotFound("The product with "+ productType + " not found");

		else {
			return products;
		}
	}

	// Product get by type
	@GetMapping("/getById/{productId}")
	public ResponseEntity<Product> getProductById( @PathVariable("productId") Integer productId) throws Exception {
		Product productData = productService.getById(productId);

		if (productData == null) {
			throw new ProductNotFound("The product with "+ productId + " not found");
		}
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}

}
