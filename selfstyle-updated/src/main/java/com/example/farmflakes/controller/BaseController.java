package com.example.farmflakes.controller;

import com.example.farmflakes.model.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

    @Autowired
    protected LoginUser loginUser;

    @ModelAttribute("user")
    public LoginUser getLoginUser(){
        return loginUser;
    }
    
}
