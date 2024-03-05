package com.example.farmflakes.controller;

import com.example.farmflakes.model.Product;
import com.example.farmflakes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RestController()
@RequestMapping("/products1") /// "/users"
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public Collection<Product> getAllProducts(){
        return repository.findAll();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product){
         repository.delete(product);
    }

}
