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
public class FileParseRequest {

    @NotBlank
    private Integer id;
    @NotBlank
    private String claim_title;
    @NotBlank
    private String description;
}
