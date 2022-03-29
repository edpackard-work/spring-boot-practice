package com.example.controller;

import com.example.domain.User;
import com.example.exception.BadDataException;
import com.example.service.MockUserService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockUserService mockUserService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        User user1 = (new User(1, "A. Mock", true));
        User user2 = (new User(2, "B. Mocking", true));
        User user3 = (new User(3, "M. V. C. Test", false));
        ArrayList<User> users = new ArrayList<>(
                Arrays.asList(user1, user2, user3));

        when(this.mockUserService.allUsers())
                .thenReturn(users);

        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("A. Mock"))
                .andExpect(jsonPath("$[0].active").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("B. Mocking"))
                .andExpect(jsonPath("$[1].active").value(true))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("M. V. C. Test"))
                .andExpect(jsonPath("$[2].active").value(false));
    }

    @Test
    public void shouldReturnAUserById() throws Exception {
        User user140 = (new User(140, "I. M. Returned", true));

        when(this.mockUserService.findUser(140)).thenReturn(user140);

        this.mockMvc.perform(get("/user/140"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$.id").value(140))
                .andExpect(jsonPath("$.name").value("I. M. Returned"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    public void shouldHandleWhenUserIDNotFound() throws Exception {
        when(this.mockUserService.findUser(9999)).thenThrow(BadDataException.class);
        this.mockMvc.perform(get("/user/9999"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @Test
    public void shouldHandleValidPostRequest() throws Exception {

        JSONObject fakeReq = new JSONObject();
        fakeReq.put("name", "A. Postroute");
        User fakeRes = (new User( 44, "A. Postroute", true));

        when(this.mockUserService.addUser("A. Postroute")).thenReturn(fakeRes);

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(fakeReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$.id").value(44))
                .andExpect(jsonPath("$.name").value("A. Postroute"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    public void shouldHandleInvalidPostRequest() throws Exception {

        JSONObject fakeReq = new JSONObject();
        fakeReq.put("this_is_a", "bad_request");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(fakeReq)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @Test
    public void shouldHandleValidDeleteRequest() throws Exception {
        this.mockMvc.perform(delete("/user/1"))
                .andExpect(status().is(204))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
        verify(mockUserService).deleteUser(1);
    }

    @Test
    public void shouldHandleDeleteRequestWithNonExistentId() throws Exception {
        doThrow(BadDataException.class)
                .when(mockUserService)
                .deleteUser(9999);
        this.mockMvc.perform(delete("/user/9999"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @Test
    public void shouldHandleValidPutRequest() throws Exception {
        this.mockMvc.perform(put("/user/10"))
                .andExpect(status().is(204))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
        verify(mockUserService).toggleStatus(10);
    }

    @Test
    public void shouldHandlePutRequestWithNonExistentId() throws Exception {
        doThrow(BadDataException.class)
                .when(mockUserService)
                .toggleStatus(3993);
        this.mockMvc.perform(put("/user/3993"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }
}
