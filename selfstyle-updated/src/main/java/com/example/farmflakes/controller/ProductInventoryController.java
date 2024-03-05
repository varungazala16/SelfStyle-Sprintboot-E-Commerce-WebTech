package com.example.farmflakes.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.farmflakes.model.Cart;
import com.example.farmflakes.model.ProductInventory;
import com.example.farmflakes.repository.ProductInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductInventoryController extends BaseController{

    @Autowired
    private ProductInventoryRepository repository;

    @Autowired
    private Cart cart;

    @GetMapping
    public List<ProductInventory> getProductInventories() {
        return repository.findAll();
    }

    @ModelAttribute("productInventory")
    public List<ProductInventory> getAvailableProductInventory() {
        List<ProductInventory> allProducts = repository.findAll();
        List<ProductInventory> result = new ArrayList<>();
        for (ProductInventory inventory : allProducts) {
            if (inventory.getQuantity() > 0) {
                result.add(inventory);
            }
        }
        return result;
    }

    @ModelAttribute("cart")
    public Cart getCart(){
        return cart;
    }

    @GetMapping("/products")
    public ModelAndView getProductsPage() {
        return new ModelAndView("products");
    }

    @PostMapping("/products")
    public ModelAndView addToCart(@RequestParam("productInventoryId") int productInventoryId) {
        ModelAndView modelAndView = new ModelAndView("products");
        ProductInventory productInventory = repository.getById(productInventoryId);
        cart.addToCart(productInventory);
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }


}
