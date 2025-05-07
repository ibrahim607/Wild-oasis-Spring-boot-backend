package com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private long EMPLOYEE_ID;

    private String displayName;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime  lastSignedIn;

    private String employeeRole;

}
