package com.ibrahim.Wild.oasis.DTOS.GuestDTOS;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestResponseDTO {
    private Long columnId;
    private String countryFlag;
    private LocalDateTime createdAt;
    private String email;
    private String fullName;
    private String nationality;
}
