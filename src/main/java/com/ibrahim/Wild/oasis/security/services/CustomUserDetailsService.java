package com.ibrahim.Wild.oasis.security.services;

import com.ibrahim.Wild.oasis.entities.Employee;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        String role = "ROLE_" + employee.getEmployeeRole().toUpperCase();
        log.info("Loading user: {} with role: {}", email, role);

        return  User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .roles(employee.getEmployeeRole())
                .authorities(new SimpleGrantedAuthority(role))
                .build();
    }
}