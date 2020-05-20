package com.sandesh.ekinmelservices.services;

import com.sandesh.ekinmelservices.models.User;
import com.sandesh.ekinmelservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getSingleByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public long countUserByUsername(User user) {
        return userRepository.countByUsername(user.getUsername());
    }
}