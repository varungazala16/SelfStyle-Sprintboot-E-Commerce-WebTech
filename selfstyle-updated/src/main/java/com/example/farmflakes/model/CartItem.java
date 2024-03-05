package com.example.farmflakes.model;

import java.util.Objects;

public class CartItem {
    private Product product;
    private ProductInventory productInventory;
    private int quantity;
    private double rate;

    public CartItem(ProductInventory productInventory, int quantity) {
        this.product = productInventory.getProduct();
        this.productInventory = productInventory;
        this.quantity = quantity;
        this.rate = productInventory.getRate();
    }

    // cart item price
    public double getCartItemPrice() {
        return quantity * rate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Double.compare(cartItem.rate, rate) == 0 && Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, rate);
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }
}
