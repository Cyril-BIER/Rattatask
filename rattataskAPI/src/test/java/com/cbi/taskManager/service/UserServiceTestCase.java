package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestCase {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService();

    @Test
    public void get1UserTest(){
        List<UserDTO> expected =List.of( new UserDTO(1L, "bob@mail.com"));
        when(userRepository.findAllById(List.of(1L))).thenReturn(List.of(
                new User(1L, "bob@mail.com", "password")
        ));
        assertEquals(expected, userService.getUsers(List.of(1L)));
    }
}
