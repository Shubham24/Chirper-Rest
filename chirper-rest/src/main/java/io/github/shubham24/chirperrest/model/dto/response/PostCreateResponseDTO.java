package io.github.shubham24.chirperrest.model.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateResponseDTO {
    
    UUID id;
    String post;
    String username;
    LocalDateTime createdTimestamp;
    LocalDateTime updatedTimestamp;
}
