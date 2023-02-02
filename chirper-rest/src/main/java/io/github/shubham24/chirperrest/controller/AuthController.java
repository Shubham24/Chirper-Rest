package io.github.shubham24.chirperrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.shubham24.chirperrest.model.dto.UserRegisterDTO;
import io.github.shubham24.chirperrest.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login() {
        return "YOU CALLED THE LOGIN ENDPOINT";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDTO registerDTO) {
        return authenticationService.register(registerDTO);
    }
}
