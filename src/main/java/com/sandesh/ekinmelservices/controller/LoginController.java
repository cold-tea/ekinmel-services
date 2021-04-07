package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.exception.InvalidUserCredentialException;
import com.sandesh.ekinmelservices.exception.UserNotFoundException;
import com.sandesh.ekinmelservices.filter.JwtUtil;
import com.sandesh.ekinmelservices.model.ChangeLogin;
import com.sandesh.ekinmelservices.model.Login;
import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.CustomUserDetailsService;
import com.sandesh.ekinmelservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/login")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public User validateUser(@RequestBody Login login) {
        User user = userService.getSingleByUsername(login.getUsername());
        if (user == null)
            throw new UserNotFoundException("User " + login.getUsername() + " not found !!");
        if (passwordEncoder.matches(login.getPassword(), user.getPassword()))
            return user;
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username and password does not match !!");
    }

    @PostMapping(value = "/jwtAuthenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new InvalidUserCredentialException("Invalid user credentials provided");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(login.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        Map<String, Object> userMap = new HashMap<>();
        User user = this.userService.getSingleByUsername(login.getUsername());
        userMap.put("user", user);
        userMap.put("token", jwtToken);
        return ResponseEntity.ok().body(userMap);
    }

    @PostMapping(value = "/change")
    public void changePassword(@RequestBody ChangeLogin login) {
        if (login == null || login.getUsername() == null)
            throw new IllegalArgumentException("Not all required parameters are passed");

        User user = userService.getSingleByUsername(login.getUsername());
        if (user == null)
            throw new UserNotFoundException("User " + login.getUsername() + " does not exist !!");
        else if (!passwordEncoder.matches(login.getPassword(), user.getPassword()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid password for user " + user.getUsername());
        else if (!login.getNewPassword().equals(login.getConfirmPassword()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password confirmation failed!!");

        user.setPassword(passwordEncoder.encode(login.getNewPassword()));
        userService.saveUser(user);
    }

    @GetMapping(value = "/validateToken")
    @ResponseStatus(HttpStatus.OK)
    public void tokenValidityTest() { }
}
