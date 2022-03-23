package com.example.service;

import com.example.domain.User;
import com.example.exception.BadDataException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MockUserServiceTest {

    MockUserService service = new MockUserService(true);

    @Test
    public void shouldReturnAllUsers() {
        ArrayList<User> result = service.allUsers();

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("A. User", result.get(0).getName());
        assertTrue(result.get(0).isActive());

        assertEquals(2, result.get(1).getId());
        assertEquals("B. Prepared", result.get(1).getName());
        assertTrue(result.get(1).isActive());

        assertEquals(3, result.get(2).getId());
        assertEquals("C. U. Later", result.get(2).getName());
        assertFalse(result.get(2).isActive());
    }

    @Test
    public void shouldReturnUserById() throws BadDataException {
        User testUser = (service.findUser(2));

        assertEquals(2, testUser.getId());
        assertEquals( "B. Prepared", testUser.getName());
        assertTrue(testUser.isActive());
    }

    @Test
    public void shouldThrowBadDataExceptionIfIdNotFound() {
        BadDataException e = assertThrows(BadDataException.class, () -> {
            service.findUser(4);});
    }
}
