package com.example.service;

import com.example.domain.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MockUserServiceTest {

    MockUserService service = new MockUserService();

    @Test
    public void shouldReturnAllUsers() {
        ArrayList<User> result = service.allUsers();

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("A. User", result.get(0).getName());
        assertTrue(result.get(0).getActive());

        assertEquals(2, result.get(1).getId());
        assertEquals("B. Prepared", result.get(1).getName());
        assertTrue(result.get(1).getActive());

        assertEquals(3, result.get(2).getId());
        assertEquals("C. U. Later", result.get(2).getName());
        assertFalse(result.get(2).getActive());
    }

    @Test
    public void shouldReturnUserById() {
        User testUser = (service.findUser(2));

        assertEquals(2, testUser.getId());
        assertEquals( "B. Prepared", testUser.getName());
        assertTrue(testUser.getActive());
    }

    @Test
    public void shouldReturnNullIfIdNotFound() {
        User testUser = (service.findUser(4));

        assertNull(testUser);
    }
}
