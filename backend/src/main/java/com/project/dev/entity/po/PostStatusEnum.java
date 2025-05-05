package com.project.dev.entity.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PostStatusEnum {
    DRAFT("Draft"),
    PUBLISHED("Published"),
    ARCHIVED("Archived"),
    DELETED("Deleted");

    private String status;

}