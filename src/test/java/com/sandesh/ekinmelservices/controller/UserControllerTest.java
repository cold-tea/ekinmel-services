package com.sandesh.ekinmelservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandesh.ekinmelservices.model.Authority;
import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserByUsername_userExists() throws Exception {
        String testUsername = "sandesh";
        User user = User.builder().username(testUsername).firstName(testUsername)
                .lastName("pokhrel").email("mail@provider.com").build();
        given(userService.getSingleByUsername(anyString())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/users/{username}", testUsername)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").exists());

        verify(userService).getSingleByUsername(anyString());
    }

    @Test
    void getUserByUsername_userDoesNotExist() throws Exception {
        String testUsername = "invalid_user";
        given(userService.getSingleByUsername(anyString())).willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/users/{username}", testUsername).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("exMessage").isNotEmpty())
                .andExpect(jsonPath("operation").value("Unknown Operation"));

        verify(userService).getSingleByUsername(anyString());
    }

    @Test
    void saveUser_userExistsException() throws Exception {
        String testUsername = "sandesh";
        Authority authority = new Authority("ROLE_QA");
        User user = User.builder().username(testUsername).firstName(testUsername).lastName("pokhrel")
                .email("some@provider.com").mobile("9843434").password("sandesh")
                .authorities(Collections.singletonList(authority)).build();

        given(userService.countUserByUsername(anyString())).willReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/users")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService).countUserByUsername(testUsername);
    }

    @Test
    void saveUser_shouldPass() throws Exception {
        String testUsername = "hello";
        Authority authority = new Authority("ROLE_QA");
        User user = User.builder().username(testUsername).firstName(testUsername).lastName("hi").enabled('Y')
                .email("some@provider.com").mobile("984343461").password("hello")
                .authorities(Collections.singletonList(authority)).build();
        String userJsonString = new ObjectMapper().writeValueAsString(user);
        System.out.println(userJsonString);
        given(userService.countUserByUsername(anyString())).willReturn(0L);
        doNothing().when(userService).saveUser(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/users")
                .content(userJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(userService).countUserByUsername(testUsername);
        verify(userService).saveUser(any());
    }

    @Test
    void updateUser_userNotFoundException() throws Exception {
        String testUsername = "invalid_user";
        Authority authority = new Authority("ROLE_QA");
        User user = User.builder().username(testUsername).firstName(testUsername).lastName("hi").enabled('Y')
                .email("some@provider.com").mobile("984343461").password("hello")
                .authorities(Collections.singletonList(authority)).build();
        String userJsonString = new ObjectMapper().writeValueAsString(user);
        given(userService.getSingleByUsername(anyString())).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/users/{username}", testUsername)
                .content(userJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService).getSingleByUsername(testUsername);
    }

    @Test
    void updateUser_cannotUpdateUsername_invalidRequest() throws Exception {
        String testUsername = "success";
        String testChangedUsername = "user_changed";
        Authority authority = new Authority("ROLE_QA");
        User user = User.builder().username(testChangedUsername).firstName(testUsername).lastName("hi").enabled('Y')
                .email("some@provider.com").mobile("984343461").password("hello")
                .authorities(Collections.singletonList(authority)).build();
        String userJsonString = new ObjectMapper().writeValueAsString(user);
        given(userService.getSingleByUsername(anyString())).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/users/{username}", testUsername)
                .content(userJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}