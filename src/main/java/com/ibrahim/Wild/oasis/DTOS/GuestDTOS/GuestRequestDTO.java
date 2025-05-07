package com.ibrahim.Wild.oasis.DTOS.GuestDTOS;

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
public class GuestRequestDTO {

    @NotBlank
    private String countryFlag;

    @PastOrPresent
    private LocalDateTime createdAt;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String nationalId;

    @NotBlank
    private String nationality;

    @NotBlank
    private String password;
}

