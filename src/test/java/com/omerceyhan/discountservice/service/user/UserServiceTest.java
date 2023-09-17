package com.omerceyhan.discountservice.service.user;

import com.omerceyhan.discountservice.entity.User;
import com.omerceyhan.discountservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findUserByIdTest() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User(1L, "Omer Ceyhan", null)));
        User user = userService.findUserById(1L);
        assertEquals("Omer Ceyhan", user.getFullName());
        verify(userRepository, times(1)).findById(any());

    }
}
