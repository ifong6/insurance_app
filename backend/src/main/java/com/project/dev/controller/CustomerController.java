package com.project.dev.controller;

import com.project.dev.entity.po.CustomerPO;
import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.response.BaseResponse;
import com.project.dev.entity.response.StatusEnum;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.BaseException;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/signUp")
    public BaseResponse<SessionCustomerVO> customerSignUp(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserRegisterRequest request) throws BaseException {
        SessionCustomerVO registeredUser = customerService.signUp(request);

        return new BaseResponse<>(
                StatusEnum.CREATED.getCode(),
                StatusEnum.CREATED.getMessage(),
                "User registered successfully",
                registeredUser
        );
    }

    @PostMapping("/logIn")
    public BaseResponse<CustomerPO> customerLogIn(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserLoginRequest request) throws BaseException {

        CustomerPO existingCustomer = customerService.logIn(request);

        return new BaseResponse<>(
                StatusEnum.CREATED.getCode(),
                StatusEnum.CREATED.getMessage(),
                "User is now logged in",
                existingCustomer
        );
    }


}
