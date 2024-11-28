package com.cbi.taskManager.controller;

import com.cbi.taskManager.dto.LoginResponse;
import com.cbi.taskManager.dto.LoginUserDTO;
import com.cbi.taskManager.dto.RegisterUserDTO;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterUserDTO request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDTO loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
