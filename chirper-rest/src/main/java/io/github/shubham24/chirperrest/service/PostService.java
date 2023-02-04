package io.github.shubham24.chirperrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.shubham24.chirperrest.model.dao.PostDAO;
import io.github.shubham24.chirperrest.model.dao.UserDAO;
import io.github.shubham24.chirperrest.model.dto.request.PostCreateRequestDTO;
import io.github.shubham24.chirperrest.model.dto.response.PostCreateResponseDTO;
import io.github.shubham24.chirperrest.repository.PostRepository;
import io.github.shubham24.chirperrest.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public PostCreateResponseDTO create(UserDAO user, PostCreateRequestDTO request) {

        if (request.getPost().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot submit an empty post");
        }

        if (request.getPost().length() > 250) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post is greater than 250 characters");
        }

        PostDAO post = new PostDAO();

        post.setPost(request.getPost());
        post.setUser(user);

        PostDAO savedPost = postRepository.save(post);

        return PostCreateResponseDTO.builder().id(savedPost.getId()).username(savedPost.getUser().getUsername())
                .createdTimestamp(savedPost.getCreatedTimestamp()).updatedTimestamp(savedPost.getUpdatedTimestamp())
                .post(savedPost.getPost())
                .build();

    }

    public List<PostDAO> getUserPosts(String username) {
        UserDAO user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find posts"));
        return postRepository.getAllPostsByUser(user);
    }
}
