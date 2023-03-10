package io.github.shubham24.chirperrest.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponseDTO {
    
    UUID id;
    String post;
    String username;
    LocalDateTime createdTimestamp;
    LocalDateTime updatedTimestamp;
}
