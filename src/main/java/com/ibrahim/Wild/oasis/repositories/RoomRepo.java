package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
}
