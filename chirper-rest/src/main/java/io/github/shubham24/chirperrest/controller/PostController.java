package io.github.shubham24.chirperrest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.shubham24.chirperrest.model.dao.PostDAO;
import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.request.PostCreateRequestDTO;
import io.github.shubham24.chirperrest.model.dto.response.PostResponseDTO;
import io.github.shubham24.chirperrest.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    PostService postService;
    
    @PostMapping("/create")
    public PostResponseDTO create(@AuthenticationPrincipal UserDAO user, @RequestBody PostCreateRequestDTO post) {
       return postService.create(user, post);
    }

    @GetMapping("/posts/{username}")
    public List<PostResponseDTO> getUserPosts(@PathVariable String username) {
        return postService.getUserPosts(username);
    }

    @DeleteMapping("/{id}")
    public PostResponseDTO delete(@AuthenticationPrincipal UserDAO user, @PathVariable UUID id) {
        return postService.delete(user, id);
    }
}
