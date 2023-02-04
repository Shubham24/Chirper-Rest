package io.github.shubham24.chirperrest.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponseDTO {
    private String username;
    private String firstname;
    private String lastname;
}
