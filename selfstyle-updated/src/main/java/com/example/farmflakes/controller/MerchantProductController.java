package com.example.farmflakes.controller;

import java.util.List;

import com.example.farmflakes.model.LoginUser;
import com.example.farmflakes.model.Product;
import com.example.farmflakes.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MerchantProductController extends BaseController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LoginUser loginUser;

    @ModelAttribute("products")
    public List<Product> getAllProducts() {
        return productRepository.findByMerchant(loginUser.getMerchant());
    }


    @GetMapping("/merchant-products")
    public ModelAndView getMerchantProductsPage() {
        if (loginUser.isLoggedOut()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("merchant-products");
    }

    @GetMapping("/add-product")
    public ModelAndView addProduct() {
        if (loginUser.isLoggedOut()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("add-product");
    }

    @PostMapping("/add-product")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        product.setMerchant(loginUser.getMerchant());
        productRepository.save(product);
        return new ModelAndView("redirect:/merchant-products");
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView editProduct(@PathVariable("id") int productId) {
        ModelAndView modelAndView = new ModelAndView("edit-product");
        Product product = productRepository.findByProductIdAndMerchant(productId, loginUser.getMerchant());
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit-product/{id}")
    public ModelAndView editSaveProduct(@PathVariable("id") int productId, @ModelAttribute Product product) {
        Product p = productRepository.getById(productId);
        p.setProductImageUrl(product.getProductImageUrl());
        p.setProductName(product.getProductName());
        p.setProductDescription(product.getProductDescription());
        p.setProductCategory(product.getProductCategory());
        productRepository.save(p);
        return new ModelAndView("redirect:/merchant-products");
    }
}
