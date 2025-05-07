package com.ibrahim.Wild.oasis.repositories;

import com.ibrahim.Wild.oasis.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
}
