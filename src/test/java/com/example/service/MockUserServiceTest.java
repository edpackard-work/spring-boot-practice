package com.example.service;

import com.example.domain.User;
import com.example.exception.BadDataException;
import com.example.util.Randomizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockUserServiceTest {
    @Mock
    private Randomizer randomizer;

    @InjectMocks MockUserService service = new MockUserService(true);
    @InjectMocks MockUserService serviceFalseActive = new MockUserService(false);

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
        BadDataException e = assertThrows(BadDataException.class, () -> service.findUser(4));
    }

    @Test
    public void shouldAddUser() {

        Mockito.when(randomizer.generateRandomNumber()).thenReturn(999);
        User result = service.addUser("A. New User");
        Mockito.when(randomizer.generateRandomNumber()).thenReturn(666);
        User result2 = serviceFalseActive.addUser( "A. Nother");

        assertEquals(999, result.getId());
        assertEquals( "A. New User", result.getName());
        assertTrue(result.isActive());
        assertEquals(666, result2.getId());
        assertEquals( "A. Nother", result2.getName());
        assertFalse(result2.isActive());
    }

    @Test
    public void shouldDeleteUser() throws BadDataException {

        int expectedLength = service.allUsers().size() - 1;

        service.deleteUser(2);
        int newLength = service.allUsers().size();

        assertEquals(expectedLength, newLength);
        BadDataException e = assertThrows(BadDataException.class, () -> service.findUser(2));
    }

    @Test
    public void shouldToggleStatus() throws BadDataException {
        boolean originalStatus = service.findUser(1).isActive();
        service.toggleStatus(1);
        boolean toggledStatus = service.findUser(1).isActive();

        assertNotEquals(originalStatus, toggledStatus);

        service.toggleStatus(1);
        toggledStatus = service.findUser(1).isActive();

        assertEquals(originalStatus, toggledStatus);
    }

    @Test
    public void shouldThrowExceptionIfToggleIdNotFound() {
        BadDataException e = assertThrows(BadDataException.class, () -> service.toggleStatus(5000));
    }
}
