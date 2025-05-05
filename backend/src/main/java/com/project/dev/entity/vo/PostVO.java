package com.project.dev.entity.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostVO {
    private String title;
    private String content;
    private String author;
    private String status;
    private String createdAt;
    private String updatedAt;
}
