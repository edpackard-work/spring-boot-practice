package com.example.controller;

import com.example.domain.User;
import com.example.exception.BadDataException;
import com.example.service.MockUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User requestBody) {
        return userService.addUser(requestBody.getName());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) throws BadDataException {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity toggleUserStatus(@PathVariable int id) throws BadDataException {
        userService.toggleStatus(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
