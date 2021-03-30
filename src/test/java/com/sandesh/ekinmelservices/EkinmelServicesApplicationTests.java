package com.sandesh.ekinmelservices;

import com.sandesh.ekinmelservices.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EkinmelServicesApplication.class)
public class EkinmelServicesApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getUserByUsername() {
        String usernameToTest = "sandesh";
        ResponseEntity<User> userResponseEntity = testRestTemplate.getForEntity("/rest/users/{username}", User.class, usernameToTest);
        assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(userResponseEntity.getBody()).getUsername()).isEqualTo("fail");
    }
}
