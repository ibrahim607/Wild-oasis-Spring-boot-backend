package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.Employee;
import com.ibrahim.Wild.oasis.entities.RoomServiceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomServiceTaskRepo extends JpaRepository<RoomServiceTask , Long> {
    boolean existsByEmployee(Optional<Employee> employee);
}
