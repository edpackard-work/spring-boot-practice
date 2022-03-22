package com.example.controller;

import com.example.domain.User;
import com.example.exception.BadDataException;
import com.example.service.MockUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {

    private final MockUserService userService;

    public UserController(MockUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ArrayList<User> getUsers() {
        return userService.allUsers();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable int id) throws BadDataException {
        return userService.findUser(id);
    }
}
