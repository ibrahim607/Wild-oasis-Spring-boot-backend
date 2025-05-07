package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "GUEST_ID", nullable = false)
    private Guest guest;

    // Store both ID and name for historical record
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "Room_id", nullable = false)
    private Room room;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "NUM_ROOMS")
    private int numRooms;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "NUM_GUESTS")
    private int numGuests;

    @Column(name = "HAD_BREAKFAST")
    private boolean hadBreakfast;
}