package io.github.shubham24.chirperrest.config;




import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.UserLoginDTO;
import io.github.shubham24.chirperrest.service.AuthenticationService;



@Component
public class UserAuthenticationProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.value}")
    private int expiryTimeMilliseconds;

    @Autowired
    AuthenticationService authService;


    public String createToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiryTimeMilliseconds);

        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        return JWT.create().withIssuer(username).withIssuedAt(now).withExpiresAt(expiry).sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        UserDAO user = authService.findByUsername(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        
    }

    public Authentication validateCredentials(UserLoginDTO loginDTO) {
        UserDAO user = authService.authenticate(loginDTO);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    
}
