package com.example.farmflakes.repository;

import com.example.farmflakes.model.Merchant;
import com.example.farmflakes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByMerchant(Merchant merchant);

    Product findByProductIdAndMerchant(int productId, Merchant merchant);
}
