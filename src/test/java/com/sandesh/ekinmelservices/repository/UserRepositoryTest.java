package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByUsername() {
        String expectedUsername= "sandesh";
        String expectedMobile = "9842100964";
        User user = userRepository.findUserByUsername(expectedUsername);
        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedMobile, user.getMobile());
    }
}