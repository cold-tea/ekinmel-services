package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getSingleByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public long countUserByUsername(String username) {
        return userRepository.countByUsername(username);
    }

    public long countUserByEmail(User user) {
        return userRepository.countByEmail(user.getEmail());
    }
}
