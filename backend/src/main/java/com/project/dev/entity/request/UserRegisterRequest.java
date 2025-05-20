package com.project.dev.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    private String firstName;
    @NotBlank private String lastName;
    @NotBlank @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") private String dob;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 8) private String password;
//    private String verificationCode;
//    private String verificationToken;
}


