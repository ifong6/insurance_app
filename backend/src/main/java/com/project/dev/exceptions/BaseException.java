package com.project.dev.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class BaseException extends Exception {
    @Autowired
    public ExceptionEnum exceptionEnum;

}

