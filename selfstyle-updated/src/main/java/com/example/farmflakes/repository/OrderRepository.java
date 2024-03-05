package com.example.farmflakes.repository;

import com.example.farmflakes.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder, Integer> {
}
