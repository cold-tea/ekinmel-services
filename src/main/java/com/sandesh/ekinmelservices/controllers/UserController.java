package com.sandesh.ekinmelservices.controllers;

import com.sandesh.ekinmelservices.models.Status;
import com.sandesh.ekinmelservices.models.User;
import com.sandesh.ekinmelservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/count/{username}")
    public Long getUsercount(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        return userService.countUserByUsername(user);
    }

    @GetMapping(value = "/countEmail/{email}")
    public Long getUsercountByEmail(@PathVariable String email) {
        User user = new User();
        user.setEmail(email);
        return userService.countUserByEmail(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getSingleByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<Status> saveUser(@RequestBody User user) {
        if (user == null) throw new IllegalArgumentException("User passed is not supported");
        else if (userService.countUserByUsername(user) > 0)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User already exists!!");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Status("User already exists"));
        userService.saveUser(user);
        System.out.println("Everything good");
        return ResponseEntity.status(HttpStatus.OK).body(new Status());
    }
}
