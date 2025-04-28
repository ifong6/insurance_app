package com.project.dev.controller;

import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.UserException;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/signUp")
    public ResponseEntity<SessionCustomerVO> customerSignUp(   //SessionUserVO is to not expose data schema to outside
        @RequestBody UserRegisterRequest request) throws UserException {
        SessionCustomerVO registeredUser = customerService.signUp(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registeredUser);
    }

    @PostMapping("/logIn")
    public HttpStatus customerSignIn(   //SessionUserVO is to not expose data schema to outside
           @Validated @RequestBody UserLoginRequest request){
        try {
            // 1. validate registration
            boolean userExisted = customerService.logIn(request);

            if (!userExisted) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User not registered"
                );
            }
            // 3. Return success response
            return HttpStatus.ACCEPTED;

        } catch(Exception e){
            // 5. handle all other errors
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
