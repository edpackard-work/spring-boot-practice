package com.example.service;

import com.example.domain.User;
import com.example.exception.BadDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockUserService {
    User user1 = new User(1, "A. User", true );
    User user2 = new User (2, "B. Prepared", true);
    User user3 = new User (3, "C. U. Later", false);

    ArrayList<User> users = new ArrayList<User>(
            Arrays.asList(user1, user2, user3));

    public ArrayList<User> allUsers() {
        return users;
    }

    public User findUser(int id) throws BadDataException {
        List<User> result = users.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        if (result.size() > 0) {
            return result.get(0);
        } else {
            throw new BadDataException();
        }
    }
}
