package com.example.farmflakes.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoginUser {

    private User user;
    private Merchant merchant;
    private Customer customer;

    public void setLoginUser(User user) {
        this.user = user;
        this.merchant = user.getMerchant();
        this.customer = user.getCustomer();
    }

    public void logout() {
        this.user = null;
        this.merchant = null;
        this.customer = null;
    }

    public User getUser() {
        return user;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isLoggedOut() {
        return user == null;
    }
}
