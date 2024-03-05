package com.example.farmflakes.controller;

import java.util.List;

import com.example.farmflakes.model.LoginUser;
import com.example.farmflakes.model.Product;
import com.example.farmflakes.model.ProductInventory;
import com.example.farmflakes.model.ProductInventoryRequest;
import com.example.farmflakes.repository.ProductInventoryRepository;
import com.example.farmflakes.repository.ProductRepository;
import com.example.farmflakes.service.ProductInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionScope
public class MerchantInventoryController extends BaseController {

    @Autowired
    LoginUser loginUser;

    @Autowired
    ProductInventoryRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductInventoryService productInventoryService;

    @ModelAttribute("products")
    public List<Product> allProducts() {
        return productRepository.findByMerchant(loginUser.getMerchant());
    }

    @ModelAttribute("inventory")
    public List<ProductInventory> getProductInventory() {
        return repository.findByMerchant(loginUser.getMerchant());
    }

    @GetMapping("/product-inventory")
    public ModelAndView productInventoryPage() {
        if (loginUser.isLoggedOut()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("product-inventory");
    }

    @GetMapping("/addInventory")
    public ModelAndView showAddInventoryForm() {
        ModelAndView modelAndView = new ModelAndView("add-product-inventory");
        return modelAndView;
    }

    @PostMapping("/addInventory")
    public ModelAndView addInventory(@ModelAttribute ProductInventoryRequest productInventoryRequest) {
        productInventoryService.addProductInventory(productInventoryRequest, loginUser.getMerchant());
        return new ModelAndView("redirect:/product-inventory");
    }

    @GetMapping("/edit-product-inventory/{id}")
    public ModelAndView showEditProductInventory(@PathVariable("id") int id) {
        ProductInventory inventory = repository.findByProductInventoryIdAndMerchant(id, loginUser.getMerchant());
        ModelAndView modelAndView = new ModelAndView("edit-product-inventory");
        modelAndView.addObject("inventory", inventory);
        return modelAndView;
    }


}
