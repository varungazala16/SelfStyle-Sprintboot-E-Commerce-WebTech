package com.example.farmflakes.controller;

import java.util.Collections;
import java.util.List;

import com.example.farmflakes.model.LoginRequest;
import com.example.farmflakes.model.LoginUser;
import com.example.farmflakes.model.OrderItem;
import com.example.farmflakes.model.User;
import com.example.farmflakes.repository.MerchantRepository;
import com.example.farmflakes.repository.OrderItemRepository;
import com.example.farmflakes.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController{

    @Autowired
    UserRepository userRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    LoginUser loginUser;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView onLogin(@ModelAttribute LoginRequest request) {
        ModelAndView view = new ModelAndView("login");
        User user = userRepository.findByUsername(request.getUsername());
        //check if username is valid
        if(user == null){
            view.addObject("validationError","Invalid Username/Password");
            return view;
        }
        //check if password is valid
        if(!user.getPassword().equals(request.getPassword())){
            view.addObject("validationError","Invalid Username/Password");
            return view;
        }

        if("Customer".equalsIgnoreCase(user.getRole())){
            view = new ModelAndView("redirect:/");
            loginUser.setLoginUser(user);
            return view; 
        }

        //Valid case redirect to home page
        view = new ModelAndView("redirect:/merchant-products");
        loginUser.setLoginUser(user);
        return view;
    }

    @ModelAttribute("orderItems")
    public List<OrderItem> getMyOrders(){
        if(loginUser.getMerchant()!=null){
            return orderItemRepository.findByMerchant(loginUser.getMerchant());
        }
        return Collections.emptyList();
    }

    @GetMapping("/merchant-home")
    public ModelAndView getMerchantHome(){
        if(loginUser.isLoggedOut()){
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("merchant-home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        loginUser.logout();
        return new ModelAndView("redirect:/");
    }

}
