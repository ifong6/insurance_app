package com.project.dev.entity.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class BaseResponse<T> {
    private String code;
    private String httpMessage;
    private String message;
    private LocalDateTime timestamp;
    private T body;

    public BaseResponse(String code, String httpMessage, String message, T body) {
        this.code = code;
        this.httpMessage = httpMessage;
        this.message = message;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    /*
        static: return實類
     */
    public static <T> BaseResponse<T> success(T object) {
        return new BaseResponse<>(
                StatusEnum.CREATED.getCode(),
                StatusEnum.CREATED.getMessage(),
                "",
                object
        );
    }

    public static <T> BaseResponse<T> ok(T object) {
        return new BaseResponse<>(
                StatusEnum.OK.getCode(),
                StatusEnum.OK.getMessage(),
                "",
                object
        );
    }

    public static String successUtil(String message) {
        return message;
    }


}
