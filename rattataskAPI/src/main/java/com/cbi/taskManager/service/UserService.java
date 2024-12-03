package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UserDTO;
import com.cbi.taskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getUsers(List<Long> ids) {
        if(ids.isEmpty()) return userRepository.findAll().stream()
                .map(u->new UserDTO(u.getId(),u.getUsername()))
                .toList();
        return userRepository.findAllById(ids).stream()
                .map(u->new UserDTO(u.getId(),u.getUsername()))
                .toList();
    }
}
