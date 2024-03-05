package com.example.farmflakes.repository;

import com.example.farmflakes.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
