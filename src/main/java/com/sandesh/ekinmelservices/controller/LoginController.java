package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.exception.UserNotFoundException;
import com.sandesh.ekinmelservices.model.ChangeLogin;
import com.sandesh.ekinmelservices.model.Login;
import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/rest/login")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public User validateUser(@RequestBody Login login) {
        User user = userService.getSingleByUsername(login.getUsername());
        if (user == null)
            throw new UserNotFoundException("User " + login.getUsername() + " not found !!");
        if (bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword()))
            return user;
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username and password does not match !!");
    }

    @PostMapping(value = "/change")
    public void changePassword(@RequestBody ChangeLogin login) {
        if (login == null || login.getUsername() == null)
            throw new IllegalArgumentException("Not all required parameters are passed");

        User user = userService.getSingleByUsername(login.getUsername());
        if (user == null)
            throw new UserNotFoundException("User " + login.getUsername() + " does not exist !!");
        else if (!bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid password for user " + user.getUsername());
        else if (!login.getNewPassword().equals(login.getConfirmPassword()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password confirmation failed!!");

        user.setPassword(bCryptPasswordEncoder.encode(login.getNewPassword()));
        userService.saveUser(user);
    }
}
