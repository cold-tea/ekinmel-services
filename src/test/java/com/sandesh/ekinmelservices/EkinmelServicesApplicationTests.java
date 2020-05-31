package com.sandesh.ekinmelservices;

import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EkinmelServicesApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void userServiceGetSingle() {
        User user = userService.getSingleByUsername("sandesh");
        assertEquals("sandesh.pokhrel56@gmail.com", user.getEmail());
        assertEquals("ROLE_USER", user.getAuthorities().get(0).getRole());
    }

    @Test
    void userAddController() {
        ResponseEntity<User> respUser = restTemplate.getForEntity("http://localhost:8081/rest/users/sandesh", User.class);
        assertEquals(HttpStatus.OK, respUser.getStatusCode());
        assertTrue(respUser.getBody() instanceof User);
    }

}
