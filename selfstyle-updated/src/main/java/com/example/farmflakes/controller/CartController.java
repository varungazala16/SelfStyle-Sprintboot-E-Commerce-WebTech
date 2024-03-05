package com.example.farmflakes.controller;

import com.example.farmflakes.model.Cart;
import com.example.farmflakes.model.Customer;
import com.example.farmflakes.model.CustomerDetails;
import com.example.farmflakes.model.LoginUser;
import com.example.farmflakes.model.MyOrder;
import com.example.farmflakes.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Cart cart;

    @GetMapping("/cart")
    public ModelAndView getCartPage() {
        return new ModelAndView("cart");
    }

    @ModelAttribute("cart")
    public Cart getCart() {
        return cart;
    }

    @PostMapping("/cart")
    public ModelAndView proceedToPayment() {
        if(getLoginUser()!=null && getLoginUser().getCustomer()!=null){
            Customer customer = getLoginUser().getCustomer();
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setAddress(customer.getAddress());
            customerDetails.setCustomerName(customer.getCustomerName());
            customerDetails.setEmail(customer.getEmailId());
            customerDetails.setMobile(customer.getMobile());
            customerDetails.setPincode(customer.getPincode());
            return confirmOrder(customerDetails);
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/confirm-order")
    public ModelAndView confirmOrder(@ModelAttribute CustomerDetails customerDetails) {
        MyOrder order = orderService.placeOrder(cart, customerDetails);
        cart.flush();
        ModelAndView modelAndView = new ModelAndView("confirm-order");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

}
