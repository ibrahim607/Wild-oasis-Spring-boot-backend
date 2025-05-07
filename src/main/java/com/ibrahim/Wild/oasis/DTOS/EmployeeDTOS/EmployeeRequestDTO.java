package com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {

    @NotBlank
    private String displayName;

    @Email
    @NotBlank
    private String email;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime lastSignedIn;

    @NotBlank
    private String employeeRole;

    @NotBlank
    private String password;
}

