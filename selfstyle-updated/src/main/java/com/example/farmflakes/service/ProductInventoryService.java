package com.example.farmflakes.service;

import com.example.farmflakes.model.Merchant;
import com.example.farmflakes.model.Product;
import com.example.farmflakes.model.ProductInventory;
import com.example.farmflakes.model.ProductInventoryRequest;
import com.example.farmflakes.repository.ProductInventoryRepository;
import com.example.farmflakes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    public void addProductInventory(ProductInventoryRequest request, Merchant merchant) {
        Product product = productRepository.getById(request.getProductId());
        ProductInventory inventory = new ProductInventory();
        inventory.setProduct(product);
        inventory.setMerchant(merchant);
        inventory.setQuantity(request.getQuantity());
        inventory.setRate(request.getRate());
        productInventoryRepository.save(inventory);
    }
}
