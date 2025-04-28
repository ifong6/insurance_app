package com.project.dev.exceptions;

public class UserException extends Exception {
    private final ExceptionEnum exceptionEnum;

    public UserException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public String getCode() {
        return exceptionEnum.getCode();
    }

    public String getMessage() {
        return exceptionEnum.getMessage();
    }
}

