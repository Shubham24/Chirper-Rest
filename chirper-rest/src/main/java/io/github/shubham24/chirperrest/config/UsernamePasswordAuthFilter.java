package io.github.shubham24.chirperrest.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.shubham24.chirperrest.model.dto.request.UserLoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter  {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserAuthenticationProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        if("/api/v1/auth/login".equals(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod())) {
            UserLoginDTO loginDTO = mapper.readValue(request.getInputStream(), UserLoginDTO.class);

            try {
                SecurityContextHolder.getContext().setAuthentication(provider.validateCredentials(loginDTO));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }

        filterChain.doFilter(request, response);
        
    }

    
}