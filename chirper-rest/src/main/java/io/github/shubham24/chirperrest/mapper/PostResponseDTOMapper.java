package io.github.shubham24.chirperrest.mapper;

import org.springframework.stereotype.Service;

import io.github.shubham24.chirperrest.model.dao.PostDAO;
import io.github.shubham24.chirperrest.model.dto.response.PostResponseDTO;

@Service
public class PostResponseDTOMapper {
    public PostResponseDTO map(PostDAO post) {
        return PostResponseDTO
            .builder()
            .id(post.getId())
            .username(post.getUser().getUsername())
            .post(post.getPost())
            .createdTimestamp(post.getCreatedTimestamp())
            .updatedTimestamp(post.getUpdatedTimestamp())
            .build();
    }

}
