package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest , Long> {
    boolean existsByEmail(String email);
}
