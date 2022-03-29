package com.example.service;

import com.example.domain.User;
import com.example.exception.BadDataException;

import com.example.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class MockUserService {

    ArrayList<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(1, "A. User", true ),
                    new User (2, "B. Prepared", true),
                    new User (3, "C. U. Later", false)
                )
            );

    boolean active;

    @Autowired
    Randomizer randomizer;

    public MockUserService( @Value("${user.default-active}") boolean defaultActive) {
        this.active = defaultActive;
    }

    public ArrayList<User> allUsers() {
        return users;
    }

    public User findUser(int id) throws BadDataException {
       return findById(id);
    }

    public User addUser(String name) {
        int id = randomizer.generateRandomNumber();
        User newUser = new User(id, name, active);
        users.add(newUser);
        return newUser;
    }

    public void deleteUser(int id) throws BadDataException {
        User user = findUser(id);
        users.remove(user);
    }

    public void toggleStatus(int id) throws BadDataException {
        User user = findUser(id);
        user.setActive(!user.isActive());
    }

    private User findById(int id) throws BadDataException {
        List<User> result = users.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        if (result.size() == 1) {
            return result.get(0);
        } else {
            throw new BadDataException();
        }
    }

}
