package com.project.dev.service.impl;

import com.project.dev.entity.po.CustomerPO;
import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.ExceptionEnum;
import com.project.dev.exceptions.UserException;
import com.project.dev.repository.CustomerRepository;
import com.project.dev.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UtilService utilService;

    @Override
    public SessionCustomerVO signUp(UserRegisterRequest request) throws UserException {
        validateEmailUniqueness(request.getEmail());

        CustomerPO newCustomer = convertToPO(request);
        CustomerPO savedCustomer = customerRepository.save(newCustomer);

        return convertToVO(savedCustomer);
    }

    @Override
    public SessionCustomerVO logIn(UserLoginRequest request) throws UserException {
        if (UtilService.emailIsValid(request.getEmail())) {
            CustomerPO existingCustomer = customerRepository.findByEmail(request.getEmail());

            UtilService.passwordValidation(request.getPassword(), existingCustomer.getPassword());

            return convertToVO(existingCustomer);
        }

        return null;
    }

    // Helper method 1: Convert UserRegisterRequest to CustomerPO
    private CustomerPO convertToPO(UserRegisterRequest request) {
        CustomerPO customerPO = new CustomerPO();
        BeanUtils.copyProperties(request, customerPO);
        customerPO.setCreatedAt(String.valueOf(LocalDateTime.now()));
        // Add any other field mappings here
        return customerPO;
    }

    // Helper method 2: Convert CustomerPO to SessionCustomerVO
    private SessionCustomerVO convertToVO(CustomerPO customerPO) {
        SessionCustomerVO vo = new SessionCustomerVO();
        BeanUtils.copyProperties(vo, customerPO);
        // Add any other field mappings or transformations here
        return vo;
    }

    // Helper method 3: Validate email uniqueness
    private void validateEmailUniqueness(String email) throws UserException {
        if (customerRepository.existsByEmail(email)) {
            throw new UserException(ExceptionEnum.EMAIL_ALREADY_EXIST);
        }
    }


}








