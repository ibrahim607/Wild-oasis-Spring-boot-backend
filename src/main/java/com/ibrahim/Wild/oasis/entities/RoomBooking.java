package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RoomBooking")
public class RoomBooking {
    @Id
    long RoomBookingId;

    @ManyToOne
    @JoinColumn(name = "Booking_id" , nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "Room_id" , nullable = false)
    private Room room;
}
