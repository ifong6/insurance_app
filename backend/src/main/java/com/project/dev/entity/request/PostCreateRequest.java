package com.project.dev.entity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String author;
}
