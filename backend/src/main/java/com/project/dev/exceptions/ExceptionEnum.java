package com.project.dev.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
   // User Enum:
   EMAIL_ALREADY_EXIST("10001", "User's Email already exists"),
   EMAIL_NOT_VALID("10002", "User's Email not valid"),
   EMAIL_NOT_PROVIDED("10003","User's Email not provided"),
   PASSWORD_NOT_VALID("10005", "Password not valid"),
   PASSWORD_NOT_PROVIDED("10006", "Password not provided"),
   USER_NOT_FOUND("10007", "User not found"),

   // Post Enum:
   POST_NOT_FOUND("20001", "Post not found"),
   TITLE_REQUIRED("20002", "Post title is required"),
   CONTENT_REQUIRED("20003", "Post content is required"),
   AUTHOR_REQUIRED("20004", "Post author is required"),
   INVALID_STATUS("20005", "Invalid post status"),
   UNAUTHORIZED_EDIT("20006", "Not authorized to edit this post"),
   POST_ALREADY_PUBLISHED("20007", "Post is already published"),
   POST_NOT_PUBLISHED("20008", "Post is not published");

   private final String code;
   private final String message;

   ExceptionEnum(String code, String message) {
      this.code = code;
      this.message = message;
   }

}
