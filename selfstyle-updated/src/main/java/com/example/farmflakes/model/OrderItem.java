package com.example.farmflakes.model;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private MyOrder order;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    private int quantity;
    private double rate;
    private double itemPrice;
    private String orderItemStatus;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public MyOrder getOrder() {
        return order;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public boolean getPendingConfirmation(){
        return "Pending Confirmation".equals(orderItemStatus);
    }

    public boolean getOrderConfirmed(){
        return "Order Confirmed".equals(orderItemStatus);
    }

    public boolean getOrderDispatched(){
        return "Order Dispatched".equals(orderItemStatus);
    }

    public boolean getOrderDelivered(){
        return "Order Delivered".equals(orderItemStatus);
    }
}
