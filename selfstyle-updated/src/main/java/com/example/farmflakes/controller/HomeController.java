package com.example.farmflakes.controller;

import java.util.List;

import com.example.farmflakes.model.Cart;
import com.example.farmflakes.model.ProductInventory;
import com.example.farmflakes.repository.ProductInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private Cart cart;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @ModelAttribute("cart")
    public Cart getCart() {
        return cart;
    }

    @ModelAttribute("productInventory")
    public List<ProductInventory> getProducts(){
        return productInventoryRepository.findByQuantityGreaterThan(0);
    }

    @GetMapping("/")
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

}
