package com.project.dev.service.impl;

import com.project.dev.entity.po.CustomerPO;
import com.project.dev.entity.request.UserLoginRequest;
import com.project.dev.entity.request.UserRegisterRequest;
import com.project.dev.entity.vo.SessionCustomerVO;
import com.project.dev.exceptions.ExceptionEnum;
import com.project.dev.exceptions.BaseException;
import com.project.dev.exceptions.UserException;
import com.project.dev.repository.CustomerRepository;
import com.project.dev.service.interfaces.ICustomerService;
import com.project.dev.utility.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public SessionCustomerVO signUp(UserRegisterRequest request) throws UserException {
        validateEmailUniqueness(request.getEmail());

        CustomerPO newCustomer = convertToPO(request);
        CustomerPO savedCustomer = customerRepository.save(newCustomer);

        return convertToVO(savedCustomer);
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
        BeanUtils.copyProperties(customerPO, vo);
        // Add any other field mappings or transformations here
        return vo;
    }

    // Helper method 3: Validate email uniqueness
    private void validateEmailUniqueness(String email) throws UserException {
        if (customerRepository.existsByEmail(email)) {
            throw new UserException(ExceptionEnum.EMAIL_ALREADY_EXIST);
        }
    }

    @Override
    @Transactional
    public CustomerPO logIn(UserLoginRequest request) throws BaseException {
        if (emailIsValid(request.getEmail())) {
            CustomerPO existingCustomer = customerRepository.findByEmail(request.getEmail());

            passwordValidation(request.getPassword(), existingCustomer.getPassword());

            // 3. Generate JWT token using email as the subject
            String newToken = JwtUtil.generateToken(existingCustomer.getEmail());

            // 4. Attach the token to the user object (optional, could also return it directly)
            existingCustomer.setToken(newToken);

            return existingCustomer;
        }
        return null;
    }

    private boolean emailIsValid(String email) throws BaseException {
        if (email == null || email.isEmpty()){
            throw new UserException(ExceptionEnum.EMAIL_NOT_PROVIDED);
        }

        if (!formatIsValid(email)) {
            throw new UserException(ExceptionEnum.EMAIL_NOT_VALID);
        }

        return true;
    }

    private void passwordValidation(String password, String existingPassword) throws BaseException {
        if (password == null || password.isEmpty()) {
            throw new UserException(ExceptionEnum.PASSWORD_NOT_PROVIDED);
        }

        if (!password.equals(existingPassword)){
            throw new UserException(ExceptionEnum.PASSWORD_NOT_VALID);
        }
    }

    private boolean formatIsValid (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }
}








