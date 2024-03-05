package com.example.farmflakes.controller;

import java.util.List;

import com.example.farmflakes.model.User;
import com.example.farmflakes.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired // creating object
    private UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();
    }

}
