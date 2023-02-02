package io.github.shubham24.chirperrest.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.UserLoginDTO;
import io.github.shubham24.chirperrest.service.AuthenticationService;



@Component
public class UserAuthenticationProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Autowired
    AuthenticationService authService;


    public Authentication validateCredentials(UserLoginDTO loginDTO) {
        UserDAO user = authService.authenticate(loginDTO);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    
}
