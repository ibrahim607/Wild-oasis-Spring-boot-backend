package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLUMN_ID")
    private Long guestID;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "NATIONAL_ID")
    private String nationalId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "COUNTRY_FLAG")
    private String countryFlag;
    @Column(name = "PASSWORD")
    private String password;
}
