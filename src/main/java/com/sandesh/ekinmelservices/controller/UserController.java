package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.exception.UserExistsException;
import com.sandesh.ekinmelservices.model.Login;
import com.sandesh.ekinmelservices.model.Status;
import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("rest/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Status status;

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

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> validateUser(@RequestBody Login login) {
        User user = userService.getSingleByUsername(login.getUsername());
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not Found !!");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(login.getPassword());
        if (encoder.matches(user.getPassword(), login.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password does not match !!");
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getSingleByUsername(username);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<Status> saveUser(@RequestBody User user) {
        if (user == null) throw new IllegalArgumentException("User passed is not supported");
        else if (userService.countUserByUsername(user) > 0)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User already exists!!");
        userService.saveUser(user);
        System.out.println("Everything good");
        return ResponseEntity.status(HttpStatus.OK).body(new Status());
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Status> globalExceptionThrown(Exception ex) {
        status.setExMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
    }
}
