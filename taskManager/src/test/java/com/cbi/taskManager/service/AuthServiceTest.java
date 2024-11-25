package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthService service;


    @Test
    public void createOneUserTest(){
        UserDTO request = new UserDTO("username","password");
        service.signIn(request);
        verify(userRepository).save(new User("username","password"));
    }
}
