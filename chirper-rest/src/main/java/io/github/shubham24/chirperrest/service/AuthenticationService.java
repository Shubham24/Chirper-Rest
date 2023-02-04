package io.github.shubham24.chirperrest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.UserLoginDTO;
import io.github.shubham24.chirperrest.model.dto.UserRegisterDTO;
import io.github.shubham24.chirperrest.model.dto.UserRegisterResponseDTO;
import io.github.shubham24.chirperrest.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserRegisterResponseDTO register(UserRegisterDTO registerDTO) {
        Optional<UserDAO> existingUser = userRepository.findByUsername(registerDTO.getUsername());

        if(existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        UserDAO user = new UserDAO();

        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        userRepository.save(user);

        return UserRegisterResponseDTO.builder().username(user.getUsername()).firstname(user.getFirstName()).lastname(user.getLastName()).build();

    }

    public UserDAO authenticate(UserLoginDTO loginDTO) {
        UserDAO user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Username or password"));

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }

        return user;
    }

    public UserDAO findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
    
}
