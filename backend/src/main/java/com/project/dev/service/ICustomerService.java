package com.project.dev.service;

import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.BaseException;
import com.project.dev.exceptions.UserException;

public interface ICustomerService {
    SessionCustomerVO signUp(UserRegisterRequest request) throws UserException;

    SessionCustomerVO logIn(UserLoginRequest request) throws BaseException;

//    void logOut();

}
