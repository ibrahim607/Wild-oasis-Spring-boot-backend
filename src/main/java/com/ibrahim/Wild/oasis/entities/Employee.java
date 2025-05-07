package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "LAST_SIGN_IN")
    private LocalDateTime  lastSignedIn;
    @Column(name = "EMPLOYEE_ROLE")
    private String employeeRole;
    @Column(name = "PASSWORD")
    private String password;
}
