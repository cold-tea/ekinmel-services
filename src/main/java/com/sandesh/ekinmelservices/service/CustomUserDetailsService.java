package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.CustomUserDetails;
import com.sandesh.ekinmelservices.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService usersService;

    public CustomUserDetailsService(UserService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.usersService.getSingleByUsername(username);
        if (Objects.isNull(user)) throw new UsernameNotFoundException("User not found");
        return new CustomUserDetails(user);
    }
}
