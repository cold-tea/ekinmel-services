package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.exception.UserExistsException;
import com.sandesh.ekinmelservices.exception.UserNotFoundException;
import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/rest/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping(value = "/count/{username}")
    public Long getUsercount(@PathVariable String username) {
        return userService.countUserByUsername(username);
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
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Gets user by username.",
            notes = "Provided a username will search and return user if present",
            httpMethod = "GET Method",
            response = User.class)
    public User getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getSingleByUsername(username);
        if (user == null) throw new UserNotFoundException("User " + username + " not found !!");
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        if (user == null) throw new IllegalArgumentException("User passed is not supported");
        else if (userService.countUserByUsername(user.getUsername()) > 0)
            throw new UserExistsException("User " + user.getUsername() + " already exists !!");
        user.setRegisterDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        if (!username.equalsIgnoreCase(user.getUsername()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username cannot be updated");
        User existingUser = userService.getSingleByUsername(username);
        if (Objects.isNull(existingUser))
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User " + user.getUsername() + " not found !!");
        user.setUsername(username);
        user.setRegisterDate(existingUser.getRegisterDate());
        user.setPassword(existingUser.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("User " + username + " updated");
    }
}
