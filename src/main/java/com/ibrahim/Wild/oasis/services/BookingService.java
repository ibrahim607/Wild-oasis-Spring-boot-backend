package com.ibrahim.Wild.oasis.services;

import com.ibrahim.Wild.oasis.DTOMapper.DTOMapper;
import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingResponseDTO;
import com.ibrahim.Wild.oasis.entities.Booking;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceConflictException;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepo bookingRepo;
    private final DTOMapper dtoMapper;

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepo.findAll().stream().map(dtoMapper::convertToBookingDTO).toList();
    }

    public Optional<BookingResponseDTO> getBookingByID(long id){
        Optional<BookingResponseDTO> booking = bookingRepo.findById(id).map(dtoMapper::convertToBookingDTO);
        if (booking.isEmpty()) {
            throw new ResourceNotFoundException(STR."Booking with id \{id} not found!");
        }
        return booking;
    }

    public BookingResponseDTO addBooking (BookingRequestDTO bookingRequestDTO){
        Optional<Booking> existingBooking = bookingRepo.findByRoomNumberAndStartDateAndEndDate(
                bookingRequestDTO.getRoomNumber(),
                bookingRequestDTO.getStartDate(),
                bookingRequestDTO.getEndDate());

        if (existingBooking.isPresent()) {
            throw new ResourceConflictException(STR."Room \{bookingRequestDTO.getRoomNumber()} is already booked during the selected period");
        }
        Booking booking =dtoMapper.convertToBooking(bookingRequestDTO);
        bookingRepo.save(booking);
        return dtoMapper.convertToBookingDTO(booking);
    }

    public BookingResponseDTO updateBooking(long id , BookingRequestDTO bookingRequestDTO){
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Booking with id \{id} not found!"));

        Optional<Booking> existingBooking = bookingRepo.findByRoomNumberAndStartDateAndEndDate(
                bookingRequestDTO.getRoomNumber(),
                bookingRequestDTO.getStartDate(),
                bookingRequestDTO.getEndDate());

        if (existingBooking.isPresent()) {
            throw new ResourceConflictException(STR."Room \{bookingRequestDTO.getRoomNumber()} is already booked during the selected period");
        }

        booking.setStartDate(bookingRequestDTO.getStartDate());
        booking.setEndDate(bookingRequestDTO.getEndDate());
        booking.setNumGuests(bookingRequestDTO.getNumGuests());
        booking.setNumRooms(bookingRequestDTO.getNumRooms());
        booking.setHadBreakfast(bookingRequestDTO.isHadBreakfast());

        bookingRepo.save(booking);

        return dtoMapper.convertToBookingDTO(booking);
    }

    public void deleteBooking(long id){
        Optional<Booking> booking = bookingRepo.findById(id);
        if (booking.isEmpty()){
            throw new ResourceNotFoundException(STR."Booking with id \{id} not found!");
        }
        bookingRepo.deleteById(id);
    }
}
