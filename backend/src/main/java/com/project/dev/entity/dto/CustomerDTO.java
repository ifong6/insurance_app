package com.project.dev.entity.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {  // DTO - Web Layer

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth; // Changed from String to LocalDate
    private String email;
    private String policyNumber;

}
