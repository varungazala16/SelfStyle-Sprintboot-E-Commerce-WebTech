package com.example.farmflakes.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@SessionScope
public class Cart {
    //List to store cart items
    private List<CartItem> cartItems;
    //Total cost of all cart items
    private double cartPrice;

    //Default cart is empty
    public Cart() {
        cartItems = new ArrayList<>();
        cartPrice = 0;
    }

    // Action on add to cart, save product to cartItem list and calculate total cart price
    public void addToCart(ProductInventory inventory) {
        CartItem item = new CartItem(inventory, 1);
        //If Item already exists in cart then increase the quantity by 1
        if (cartItems.contains(item)) {
            int existingItemIndex = cartItems.indexOf(item);
            CartItem existingCartItem = cartItems.get(existingItemIndex);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } else {
            //else add the item to cart
            cartItems.add(item);
        }
        //Calculate price of cart after adding the item
        calculateCartPrice();
    }

    //Cart price is sum of all cart item prices
    private void calculateCartPrice() {
        double price = 0;
        for (CartItem item : cartItems) {
            price = price + item.getCartItemPrice();
        }
        this.cartPrice = price;
    }

    public int getCartItemCount(){
        return this.cartItems.size();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getCartPrice() {
        return cartPrice;
    }

    public void flush(){
        cartItems = new ArrayList<>();
        cartPrice = 0;
    }
}

