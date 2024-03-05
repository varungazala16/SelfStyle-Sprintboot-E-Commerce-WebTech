package com.example.farmflakes.repository;

import com.example.farmflakes.model.Merchant;
import com.example.farmflakes.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByMerchant(Merchant merchant);
}
