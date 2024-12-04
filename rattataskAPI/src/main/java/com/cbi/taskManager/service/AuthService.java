package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.LoginResponse;
import com.cbi.taskManager.dto.LoginUserDTO;
import com.cbi.taskManager.dto.RegisterUserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Transactional
    public User signUp(RegisterUserDTO request) {
        return userRepository.save(
                new User(request.email(), request.name(), request.lastName(),
                        passwordEncoder.encode(request.password()))
        );
    }

    public LoginResponse login(LoginUserDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(),
                        loginRequest.password())
        );
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow();

        return new LoginResponse(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getLastName(), 
                jwtService.generateToken(user),
                jwtService.getExpirationTime());
    }
}
