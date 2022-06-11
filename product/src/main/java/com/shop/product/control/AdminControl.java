package com.shop.product.control;

import java.util.List;
import java.util.Optional;

import com.shop.product.model.Product;
import com.shop.product.repo.ProductRepo;
import com.shop.product.service.SequenceGenerator;

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
public class AdminControl {
    @Autowired
    private ProductRepo repo;

    // @Autowired
	// private SequenceGenerator service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Product product){
       // product.setId(service.getSequenceNumber(Product.SEQUENCE_NAME));
        repo.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Product> getUser(){
        return repo.findAll();
    }

    @GetMapping("/all/{productId}")
    public Optional<Product> getUserbyID(@PathVariable String productId){
        return repo.findById(productId);
    }

    @GetMapping("/all/{category}")
    public Optional<Product> getUserbycategory(@PathVariable String category){
        return repo.findById(category);
    }

    @GetMapping("/all/{productName}")
    public Optional<Product> getUserbypname(@PathVariable String productName){
        return repo.findById(productName);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> update(@PathVariable String productId,@RequestBody Product product){
        repo.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/del/{productId}")
    public String DelUserbyName(@PathVariable String productId){
        repo.findById(productId);
        return "Product deleted with productid => " +productId;
    }
}
