package com.example.farmflakes.service;

import com.example.farmflakes.model.*;
import com.example.farmflakes.repository.CustomerRepository;
import com.example.farmflakes.repository.OrderItemRepository;
import com.example.farmflakes.repository.OrderRepository;
import com.example.farmflakes.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public MyOrder placeOrder(Cart cart, CustomerDetails customerDetails) {
        MyOrder order = new MyOrder();
        order = repository.save(order);
        order.setTotalPrice(cart.getCartPrice());
        //Order Items
        order.setOrderItems(new ArrayList<>());
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem item = new OrderItem();
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setRate(cartItem.getRate());
            item.setItemPrice(cartItem.getQuantity() * cartItem.getRate());
            item.setOrder(order);
            item.setMerchant(cartItem.getProductInventory().getMerchant());
            item.setOrderItemStatus("Confirmed");
            orderItemRepository.save(item);
            order.getOrderItems().add(item);
            //reduce inventory
            ProductInventory inventory = cartItem.getProductInventory();
            inventory.setQuantity(inventory.getQuantity()- cartItem.getQuantity());
            productInventoryRepository.save(inventory);
        }
        //Customer from customer details
        Customer customer = new Customer();
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setMobile(customerDetails.getMobile());
        customer.setEmailId(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setPincode(customerDetails.getPincode());
        customerRepository.save(customer);
        order.setCustomer(customer);
        order.setOrderStatus("Confirmed");



        return repository.save(order);
    }

}
