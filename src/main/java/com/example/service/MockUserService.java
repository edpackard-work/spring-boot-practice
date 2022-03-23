package com.example.service;

import com.example.domain.User;
import com.example.exception.BadDataException;

import com.example.util.Randomizer;
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

    ArrayList<User> users = new ArrayList<User>(
            Arrays.asList(
                    new User(1, "A. User", true ),
                    new User (2, "B. Prepared", true),
                    new User (3, "C. U. Later", false)
                )
            );

    boolean active;

    public MockUserService( @Value("${user.default-active}") boolean defaultActive) {
        this.active = defaultActive;
    }

    public ArrayList<User> allUsers() {
        return users;
    }

    public User findUser(int id) throws BadDataException {
        List<User> user = findById(id);
        if (user.size() == 1) {
            return user.get(0);
        } else {
            throw new BadDataException();
        }
    }

    public User addUser(User inputData) {
        int id = Randomizer.generateRandomNumber();
        User newUser = new User(id, inputData.getName(), active);
        users.add(newUser);
        return newUser;
    }

    public void deleteUser(int id) throws BadDataException {
        List<User> user = findById(id);
        if (user.size() == 1) {
            users.remove(user.get(0));
        } else {
            throw new BadDataException();
        }
    }

    private List<User> findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
    }

}
