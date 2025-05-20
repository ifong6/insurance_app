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

    @PostMapping("/signup")
    public BaseResponse<SessionCustomerVO> customerSignUp(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserRegisterRequest request) throws BaseException {
        SessionCustomerVO registeredUser = customerService.signUp(request);

        return BaseResponse.success(registeredUser);
    }

    @PostMapping("/login")
    public BaseResponse<SessionCustomerVO> customerLogIn(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserLoginRequest request) throws BaseException {

        SessionCustomerVO existingCustomer = customerService.logIn(request);

        return BaseResponse.ok(existingCustomer);
    }

    @PostMapping("/logout")
    public String customerLogOut(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserLoginRequest request) throws BaseException {

        return BaseResponse.successUtil("You're logged out");
    }






}
