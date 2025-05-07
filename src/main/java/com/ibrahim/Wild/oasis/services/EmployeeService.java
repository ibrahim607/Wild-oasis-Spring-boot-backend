package com.ibrahim.Wild.oasis.services;

import com.ibrahim.Wild.oasis.DTOMapper.DTOMapper;
import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.entities.Employee;
import com.ibrahim.Wild.oasis.entities.RoomServiceTask;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceConflictException;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.EmployeeRepo;
import com.ibrahim.Wild.oasis.repositories.RoomServiceTaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RoomServiceTaskRepo roomServiceTaskRepo;
    private final DTOMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepo.findAll().stream().map(dtoMapper::convertToEmployeeDTO).toList();
    }

    public Optional<EmployeeResponseDTO> getEmployeeById(long id){
        Optional<EmployeeResponseDTO> employee = employeeRepo.findById(id).map(dtoMapper::convertToEmployeeDTO);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException(STR."Employee with id \{id} not found!");
        }
        return employee;
    }

    public void addEmployee(EmployeeRequestDTO employeeRequestDTO){
        if (employeeRepo.existsByEmail(employeeRequestDTO.getEmail())){
            throw new ResourceConflictException(STR."This email already exists");
        }
        Employee employee = dtoMapper.convertToEmployee(employeeRequestDTO);
        employee.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));
        employeeRepo.save(employee);
    }

    public void updateEmployee(long id, EmployeeRequestDTO employeeRequestDTO){
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Employee with id \{id} was not found!"));

        if (!employee.getEmail().equals(employeeRequestDTO.getEmail()) &&
                employeeRepo.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new ResourceConflictException(STR."This email already exists");
        }

        employee.setEmployeeRole(employeeRequestDTO.getEmployeeRole());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));
        employee.setCreatedAt(employeeRequestDTO.getCreatedAt());
        employee.setLastSignedIn(employeeRequestDTO.getLastSignedIn());
        employee.setDisplayName(employeeRequestDTO.getDisplayName());

        employeeRepo.save(employee);
    }

    public void deleteEmployee(long id){
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isEmpty()){
            throw new ResourceNotFoundException(STR."Employee with id \{id} was not found!");
        }

        if (roomServiceTaskRepo.existsByEmployee(employee)) {
            throw new ResourceConflictException("Cannot delete employee with assigned room service tasks. Please reassign or complete the tasks first.");
        }

        employeeRepo.deleteById(id);
    }
}