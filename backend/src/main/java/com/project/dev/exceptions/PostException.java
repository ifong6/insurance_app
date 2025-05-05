package com.project.dev.exceptions;

import com.project.dev.repository.PostRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

public class PostException extends BaseException{

    public PostException(ExceptionEnum exceptionEnum) {
        super();
    }

}
