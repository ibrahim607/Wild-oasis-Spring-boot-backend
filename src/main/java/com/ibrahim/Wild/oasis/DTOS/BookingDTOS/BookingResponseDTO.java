package com.ibrahim.Wild.oasis.DTOS.BookingDTOS;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDTO {
    private int bookingId;
    private String employeeName;
    private int employeeId;
    private String guestFullName;
    private int guestId;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private LocalDateTime  createdAt;
    private int numRooms;
    private int numGuests;
    private long roomNumber;
    private boolean hadBreakfast;
}
