package com.shop.product.control;

import java.util.List;
import java.util.Optional;

import com.shop.product.model.Product;
import com.shop.product.repo.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("product/user")
public class ProductControl {

    @Autowired
    private ProductRepo repo;

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
    
}
