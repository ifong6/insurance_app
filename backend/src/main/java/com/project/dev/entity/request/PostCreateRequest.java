package com.project.dev.entity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
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
