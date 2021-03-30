package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.User;
import com.sandesh.ekinmelservices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void initilaize() {
        userService = new UserService(userRepository);
    }

    @Test
    void getSingleByUsername() {
        User user = User.builder().username("sandesh").firstName("sandesh").lastName("sandesh")
                .email("sandesh@provider.com").build();
        given(userRepository.findUserByUsername(anyString())).willReturn(user);
        User retrievedUser = this.userService.getSingleByUsername("sandesh");
        assertThat(user.getUsername()).isEqualTo(retrievedUser.getUsername());
        verify(userRepository).findUserByUsername(anyString());
    }
}