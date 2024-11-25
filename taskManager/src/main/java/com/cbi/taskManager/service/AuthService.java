package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void signIn(UserDTO request) {
        userRepository.save(
                new User(request.username(),request.password())
        );
    }
}
