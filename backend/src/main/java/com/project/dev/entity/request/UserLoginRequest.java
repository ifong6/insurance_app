package com.project.dev.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
