package com.example.farmflakes.controller;

import com.example.farmflakes.model.Customer;
import com.example.farmflakes.model.Merchant;
import com.example.farmflakes.model.RegisterRequest;
import com.example.farmflakes.model.User;
import com.example.farmflakes.repository.CustomerRepository;
import com.example.farmflakes.repository.MerchantRepository;
import com.example.farmflakes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController extends BaseController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterRequest request) {
        // Save user details
        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        userRepository.save(user);

        // Save Merchant details
        if ("Seller".equalsIgnoreCase(request.getRole())) {
            Merchant merchant = new Merchant();
            merchant.setName(request.getName());
            merchant.setEmail(request.getEmail());
            merchant.setMobile(request.getMobile());
            merchant = merchantRepository.save(merchant);
            user.setMerchant(merchant);
        }

        if ("Customer".equalsIgnoreCase(request.getRole())) {
            Customer customer = new Customer();
            customer.setCustomerName(request.getName());
            customer.setEmailId(request.getEmail());
            customer.setMobile(request.getMobile());
            customer.setAddress(request.getAddress());
            customer.setPincode(request.getPincode());
            customerRepository.save(customer);
            user.setCustomer(customer);
        }
        userRepository.save(user);

        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        modelAndView.addObject("registerSuccess", true);
        return modelAndView;
    }
}
