package io.github.shubham24.chirperrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.shubham24.chirperrest.config.UserAuthenticationProvider;
import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.UserRegisterDTO;
import io.github.shubham24.chirperrest.model.dto.UserRegisterResponseDTO;
import io.github.shubham24.chirperrest.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public String login(@AuthenticationPrincipal UserDAO user) {
        return "Bearer " + userAuthenticationProvider.createToken(user.getUsername());  
    }

    @PostMapping("/register")
    public UserRegisterResponseDTO register(@RequestBody UserRegisterDTO registerDTO) {
        return authenticationService.register(registerDTO);
    }
}
