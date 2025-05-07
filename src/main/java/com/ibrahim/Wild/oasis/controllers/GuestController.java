package com.ibrahim.Wild.oasis.controllers;

import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestResponseDTO;
import com.ibrahim.Wild.oasis.repositories.GuestRepo;
import com.ibrahim.Wild.oasis.services.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
@Validated
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAllGuests(){
       List <GuestResponseDTO> guests = guestService.getAllGuests();
       return ResponseEntity.ok(guests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> getGuestById(@PathVariable long id) {
        return guestService.getGuestByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> addGuest(@RequestBody @Valid GuestRequestDTO guestRequestDTO) {
        guestService.addGuest(guestRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateGuest(@PathVariable long id, @RequestBody @Valid GuestRequestDTO guestRequestDTO) {
        guestService.updateGuest(guestRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
