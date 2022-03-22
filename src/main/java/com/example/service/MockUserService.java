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

    ArrayList<User> users = new ArrayList<User>(
            Arrays.asList(
                    new User(1, "A. User", true ),
                    new User (2, "B. Prepared", true),
                    new User (3, "C. U. Later", false)
                )
            );

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
