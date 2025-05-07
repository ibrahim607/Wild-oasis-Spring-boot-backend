package com.ibrahim.Wild.oasis.DTOMapper;

import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.BookingDTOS.BookingResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskResponseDTO;
import com.ibrahim.Wild.oasis.entities.*;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DTOMapper {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    //Employee DTO
    public Employee convertToEmployee(EmployeeRequestDTO employeeRequestDTO){
        return Employee.builder()
                .employeeRole(employeeRequestDTO.getEmployeeRole())
                .email(employeeRequestDTO.getEmail())
                .displayName(employeeRequestDTO.getDisplayName())
                .lastSignedIn(employeeRequestDTO.getLastSignedIn())
                .password(passwordEncoder.encode(employeeRequestDTO.getPassword()))
                .createdAt(employeeRequestDTO.getCreatedAt())
                .build();
    }

    public EmployeeResponseDTO convertToEmployeeDTO(Employee employee){

        return EmployeeResponseDTO.builder()
                .EMPLOYEE_ID(employee.getEmployeeId())
                .email(employee.getEmail())
                .employeeRole(employee.getEmployeeRole())
                .displayName(employee.getDisplayName())
                .lastSignedIn(employee.getLastSignedIn())
                .createdAt(employee.getCreatedAt())
                .build();
    }


    //Booking DTO
    public BookingResponseDTO convertToBookingDTO(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingId(booking.getBookingId())
                .guestFullName(booking.getGuest().getFullName())
                .employeeName(booking.getEmployeeName())  // Use stored name
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .createdAt(booking.getCreatedAt())
                .numRooms(booking.getNumRooms())
                .numGuests(booking.getNumGuests())
                .roomNumber(booking.getRoom().getRoomId())
                .hadBreakfast(booking.isHadBreakfast())
                .employeeId(Math.toIntExact(booking.getEmployeeId()))
                .guestId(Math.toIntExact(booking.getGuest().getGuestID()))
                .build();
    }

    public Booking convertToBooking(BookingRequestDTO bookingRequestDTO) {
        Guest guest = new Guest();
        guest.setGuestID(bookingRequestDTO.getGuestId());

        Room room = new Room();
        room.setRoomId(bookingRequestDTO.getRoomNumber());

        Employee employee = employeeRepo.findById(bookingRequestDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return Booking.builder()
                .guest(guest)
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getDisplayName())
                .numGuests(bookingRequestDTO.getNumGuests())
                .endDate(bookingRequestDTO.getEndDate())
                .numRooms(bookingRequestDTO.getNumRooms())
                .startDate(bookingRequestDTO.getStartDate())
                .hadBreakfast(bookingRequestDTO.isHadBreakfast())
                .createdAt(bookingRequestDTO.getCreatedAt() != null ?
                        bookingRequestDTO.getCreatedAt() :
                        LocalDateTime.now())
                .room(room)
                .build();
    }
    //Guest DTO
    public GuestResponseDTO convertToGuestDTO(Guest guest){
        return GuestResponseDTO.builder()
                .columnId(guest.getGuestID())
                .countryFlag(guest.getCountryFlag())
                .createdAt(guest.getCreatedAt())
                .email(guest.getEmail())
                .fullName(guest.getFullName())
                .nationality(guest.getNationality())
                .build();
    }

    public Guest convertToGuest(GuestRequestDTO guestRequestDTO){
        return Guest.builder()
                .countryFlag(guestRequestDTO.getCountryFlag())
                .fullName(guestRequestDTO.getFullName())
                .email(guestRequestDTO.getEmail())
                .nationalId(guestRequestDTO.getNationalId())
                .createdAt(guestRequestDTO.getCreatedAt())
                .nationality(guestRequestDTO.getNationality())
                .build();
    }

    //RoomServiceTask DTO
    public RoomServiceTaskResponseDTO convertToRoomServiceTaskDTO(RoomServiceTask roomServiceTask){
        return RoomServiceTaskResponseDTO.builder()
                .roomServiceTaskId(roomServiceTask.getRoomServiceTaskId())
                .roomId(roomServiceTask.getRoom().getRoomId())
                .description(roomServiceTask.getDescription())
                .employeeId(roomServiceTask.getEmployee().getEmployeeId())
                .build();
    }

    public RoomServiceTask convertToRoomServiceTask(RoomServiceTaskRequestDTO roomServiceTaskRequestDTO) {
        Room room = new Room();
        room.setRoomId(roomServiceTaskRequestDTO.getRoomId());

        Employee employee = new Employee();
        employee.setEmployeeId(roomServiceTaskRequestDTO.getEmployeeId());

        return RoomServiceTask.builder()
                .room(room)
                .employee(employee)
                .description(roomServiceTaskRequestDTO.getDescription())
                .build();
    }

    //Room DTO
    public RoomResponseDTO convertToRoomDTO(Room room){
        return RoomResponseDTO.builder()
                .roomId(room.getRoomId())
                .description(room.getDescription())
                .image(room.getImage())
                .roomPrice(room.getRoomPrice())
                .build();
    }

    public Room convertToRoom(RoomRequestDTO roomRequestDTO){
        return Room.builder()
                .description(roomRequestDTO.getDescription())
                .image(roomRequestDTO.getImage())
                .roomPrice(roomRequestDTO.getRoomPrice())
                .build();
    }

}


