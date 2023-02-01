package io.github.shubham24.chirperrest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    

    @PostMapping("/login")
    public String login() {
        return "YOU CALLED THE LOGIN ENDPOINT";
    }

    @PostMapping("/register")
    public String register() {
        return "YOU CALLED THE REGISTER ENDPOINT";
    }
}
