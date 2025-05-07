package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.Booking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking , Long> {
    @Query("SELECT b FROM Booking b WHERE b.room.roomId = :roomId " +
            "AND (b.startDate < :endDate AND b.endDate > :startDate)")
    Optional<Booking> findByRoomNumberAndStartDateAndEndDate(@Param("roomId") int roomId,
                                                             @Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate);

}
