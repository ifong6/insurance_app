package com.project.dev.service;

import com.project.dev.entity.po.CustomerPO;
import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.UserException;

import java.util.List;

public interface ICustomerService {
    SessionCustomerVO signUp(UserRegisterRequest request) throws UserException;

    boolean logIn(UserLoginRequest request) throws UserException;

    CustomerPO findById(Integer id);

    CustomerPO findByEmail(CustomerPO customer);

    List<CustomerPO> findAll();

    void deleteById(Integer id);


}
