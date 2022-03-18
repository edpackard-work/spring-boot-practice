package com.example.controller;

import com.example.domain.User;
import com.example.service.MockUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class UserController {

    MockUserService userService = new MockUserService();

    @GetMapping()
    public ArrayList<User> getUsers() {
        return(userService.allUsers());
    }

    @GetMapping("{id}")
    public Optional<User> getById(@PathVariable int id) {
        return(userService.findUser(id));
    }
}
