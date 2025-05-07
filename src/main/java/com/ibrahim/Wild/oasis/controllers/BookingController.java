package com.ibrahim.Wild.oasis.controllers;

import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingResponseDTO;
import com.ibrahim.Wild.oasis.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@Validated
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable long id) {
        return bookingService.getBookingByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> addBooking(@RequestBody @Valid BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO savedBooking = bookingService.addBooking(bookingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable long id, @RequestBody @Valid BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO updatedBooking = bookingService.updateBooking(id, bookingRequestDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

