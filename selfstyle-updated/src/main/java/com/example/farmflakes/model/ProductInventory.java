package com.example.farmflakes.model;

import javax.persistence.*;

@Entity
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productInventoryId;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    Merchant merchant;

    int quantity;
    double rate;

    public int getProductInventoryId() {
        return productInventoryId;
    }

    public void setProductInventoryId(int productInventoryId) {
        this.productInventoryId = productInventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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
}
