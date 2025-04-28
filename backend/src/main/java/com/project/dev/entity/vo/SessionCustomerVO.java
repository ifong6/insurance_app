package com.project.dev.entity.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionCustomerVO {
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String policyNo;
    private String createdAt;
    private String updatedAt;
}
