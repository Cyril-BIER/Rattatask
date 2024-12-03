package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestCase {


    UserService userService = new UserService();

    @Test
    public void get1UserTest(){
        List<UserDTO> expected =List.of( new UserDTO(1L, "bob@mail.com"));
        assertEquals(expected, userService.getUsers(List.of(1L)));
    }
}
