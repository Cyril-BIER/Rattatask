package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.LoginResponse;
import com.cbi.taskManager.dto.LoginUserDTO;
import com.cbi.taskManager.dto.RegisterUserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthService service;

    @Test
    public void createOneUserTest(){
        RegisterUserDTO request = new RegisterUserDTO("username","password");
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        service.signUp(request);
        verify(userRepository).save(new User("username","encodedPassword"));
    }

    @Test
    public void loginTest(){
        LoginUserDTO loginRequest = new LoginUserDTO("username", "password");
        LoginResponse expected = new LoginResponse(null,
                "username",
                "generatedToken",
                3600000L);

        User user = new User("username", "password");
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("generatedToken");
        when(jwtService.getExpirationTime()).thenReturn(3600000L);
        LoginResponse actual = service.login(loginRequest);

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("username", "password")
        );
        assertEquals(expected,actual);
    }

}