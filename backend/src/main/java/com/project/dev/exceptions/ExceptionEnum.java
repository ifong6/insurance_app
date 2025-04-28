package com.project.dev.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
   EMAIL_ALREADY_EXIST("10001", "Email already exists"),
   EMAIL_NOT_FOUND("10002", "Email not found"),
   PASSWORD_NOT_VALID("10003", "Password not valid"),
   USER_NOT_FOUND("10004", "User not found");

   private final String code;
   private final String message;

   ExceptionEnum(String code, String message) {
      this.code = code;
      this.message = message;
   }

}
