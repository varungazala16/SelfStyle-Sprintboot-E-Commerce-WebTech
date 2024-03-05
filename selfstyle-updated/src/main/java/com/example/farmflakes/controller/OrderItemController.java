package com.example.farmflakes.controller;

import com.example.farmflakes.model.LoginUser;
import com.example.farmflakes.model.OrderItem;
import com.example.farmflakes.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class OrderItemController extends BaseController {


    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    LoginUser loginUser;

    @ModelAttribute("orderItems")
    public List<OrderItem> getMyOrders(){
        if(loginUser.getMerchant()!=null){
            return orderItemRepository.findByMerchant(loginUser.getMerchant());
        }
        return Collections.emptyList();
    }

    @PostMapping("/orderConfirmed")
    public ModelAndView orderConfirmed(@RequestParam("orderItemId") int orderItemId){
        OrderItem orderItem = orderItemRepository.getById(orderItemId);
        orderItem.setOrderItemStatus("Order Confirmed");
        orderItemRepository.save(orderItem);
        return new ModelAndView("redirect:/merchant-home");
    }

    @PostMapping("/orderDispatched")
    public ModelAndView orderDispatched(@RequestParam("orderItemId") int orderItemId){
        OrderItem orderItem = orderItemRepository.getById(orderItemId);
        orderItem.setOrderItemStatus("Order Dispatched");
        orderItemRepository.save(orderItem);
        return new ModelAndView("redirect:/merchant-home");
    }

    @PostMapping("/orderDelivered")
    public ModelAndView orderDelivered(@RequestParam("orderItemId") int orderItemId){
        OrderItem orderItem = orderItemRepository.getById(orderItemId);
        orderItem.setOrderItemStatus("Order Delivered");
        orderItemRepository.save(orderItem);
        return new ModelAndView("redirect:/merchant-home");
    }
}
