package com.example.service;

import com.example.domain.User;

import java.util.ArrayList;
import java.util.Arrays;

public class MockUserService {
    User user1 = new User(1, "A. User", true );
    User user2 = new User (2, "B. Prepared", true);
    User user3 = new User (3, "C. U. Later", false);

    ArrayList<User> users = new ArrayList<User>(
            Arrays.asList(user1, user2, user3));

    public ArrayList<User> allUsers() {
        return users;
    }
}
