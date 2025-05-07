package com.ibrahim.Wild.oasis.DTOS.BookingDTOS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDTO {

    @NotNull
    @Min(1)
    private Long guestId;

    @NotNull
    @Min(1)
    private Long employeeId;

    @NotNull
    @Min(1)
    private Integer roomNumber;


    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Min(1)
    private Integer numRooms;

    @NotNull
    @Min(1)
    private Integer numGuests;

    private boolean hadBreakfast;

    @PastOrPresent
    private LocalDateTime createdAt;
}



