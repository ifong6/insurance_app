package com.project.dev.entity.po;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "post")
public class PostPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Content is required")
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author name cannot exceed 50 characters")
    @Column(name = "author", nullable = false, length = 50)
    private String author;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    @Column(name = "status", nullable = false, length = 20)
    private String status; // e.g., "DRAFT", "PUBLISHED", "ARCHIVED"

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createTime = now;
        this.updateTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
