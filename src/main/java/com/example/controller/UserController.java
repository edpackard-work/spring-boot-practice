package com.example.controller;

import com.example.domain.User;
import com.example.service.MockUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class UserController {

    MockUserService userService = new MockUserService();

    @GetMapping()
    public ArrayList<User> getUsers() {
        return(userService.allUsers());
    }
}
