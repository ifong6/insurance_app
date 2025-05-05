package com.project.dev.entity.response;

import lombok.Getter;

@Getter
public enum StatusEnum {
    // 2xx Success
    OK("200", "OK"),
    CREATED("201", "Created"),

    // 4xx Client Error
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    CONFLICT("409", "Conflict"),

    // 5xx Server Error
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    SERVICE_UNAVAILABLE("503", "Service Unavailable");

    private final String code;
    private final String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

