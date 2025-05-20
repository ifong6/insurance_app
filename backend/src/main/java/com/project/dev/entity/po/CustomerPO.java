package com.project.dev.entity.po;

import jakarta.persistence.*;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class CustomerPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Integer customer_id;

    @NotBlank(message = "First name is required")
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false, length = 20)
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in format YYYY-MM-DD")
    @Column(nullable = false, length = 10)
    private String dob;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false, length = 25)
    private String password;

    @Column(length = 25)
    private String policyNumber;

    @Column(updatable = false)
    private String createdAt = LocalDateTime.now().toString();

    private String updatedAt;

    /*
        private String token;  // take care by client side
    */
}
