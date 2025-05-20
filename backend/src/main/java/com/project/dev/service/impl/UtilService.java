package com.project.dev.service.impl;

import com.project.dev.exceptions.ExceptionEnum;
import com.project.dev.exceptions.UserException;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public static boolean emailIsValid(String email) throws UserException {
        if (email == null || email.isEmpty()){
            throw new UserException(ExceptionEnum.EMAIL_NOT_PROVIDED);
        }

        if (!formatIsValid(email)) {
            throw new UserException(ExceptionEnum.EMAIL_NOT_VALID);
        }

        return true;
    }

    public static void passwordValidation(String password, String existingPassword) throws UserException {
        if (password == null || password.isEmpty()) {
            throw new UserException(ExceptionEnum.PASSWORD_NOT_PROVIDED);
        }

        if (!password.equals(existingPassword)){
            throw new UserException(ExceptionEnum.PASSWORD_NOT_VALID);
        }
    }

    public static boolean formatIsValid (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }
}
