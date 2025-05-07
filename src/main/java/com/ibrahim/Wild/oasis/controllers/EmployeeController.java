package com.ibrahim.Wild.oasis.controllers;

import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Validated
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public  ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        employeeService.addEmployee(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        employeeService.updateEmployee(id, employeeRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

