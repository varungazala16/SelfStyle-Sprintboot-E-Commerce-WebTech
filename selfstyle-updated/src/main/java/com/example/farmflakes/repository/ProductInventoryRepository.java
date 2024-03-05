package com.example.farmflakes.repository;

import com.example.farmflakes.model.Merchant;
import com.example.farmflakes.model.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {
    List<ProductInventory> findByMerchant(Merchant merchant);
    List<ProductInventory> findByQuantityGreaterThan(int quantity);

    ProductInventory findByProductInventoryIdAndMerchant(int productInventoryId, Merchant merchant);
}
