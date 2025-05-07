package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepo extends JpaRepository<RoomBooking , Long> {
}
