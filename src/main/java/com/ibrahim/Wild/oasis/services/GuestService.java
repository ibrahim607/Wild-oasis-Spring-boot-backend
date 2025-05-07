package com.ibrahim.Wild.oasis.services;

import com.ibrahim.Wild.oasis.DTOMapper.DTOMapper;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestResponseDTO;
import com.ibrahim.Wild.oasis.entities.Guest;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceConflictException;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.GuestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepo guestRepo;
    private final DTOMapper dtoMapper;

    public List<GuestResponseDTO> getAllGuests(){
        return guestRepo.findAll().stream().map(dtoMapper::convertToGuestDTO).toList();
    }

    public Optional<GuestResponseDTO> getGuestByID(long id){
        Optional<GuestResponseDTO> guest = guestRepo.findById(id).map(dtoMapper::convertToGuestDTO);
        if (guest.isEmpty()) {
            throw new ResourceNotFoundException(STR."Guest with id \{id} not found!");
        }
        return guest;
    }

    public void addGuest(GuestRequestDTO guestRequestDTO){
        if (guestRepo.existsByEmail(guestRequestDTO.getEmail())){
            throw new ResourceConflictException(STR."This email already exists");
        }
        Guest guest = dtoMapper.convertToGuest(guestRequestDTO);
        guestRepo.save(guest);
    }

    public void deleteGuest(long id)
    {
        Optional<Guest> guest = guestRepo.findById(id);
        if (guest.isEmpty()){
            throw new ResourceNotFoundException(STR."Guest with id \{id} not found!");
        }
        guestRepo.deleteById(id);
    }

    public void updateGuest(GuestRequestDTO guestRequestDTO , long id){
        Guest guest = guestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Guest with id \{id} not found!"));

        if (guestRepo.existsByEmail(guestRequestDTO.getEmail())){
            throw new ResourceConflictException(STR."This email already exists");
        }

        guest.setNationality(guestRequestDTO.getNationality());
        guest.setEmail(guestRequestDTO.getEmail());
        guest.setNationalId(guestRequestDTO.getNationalId());
        guest.setFullName(guestRequestDTO.getFullName());
        guest.setCountryFlag(guestRequestDTO.getCountryFlag());
        guest.setCreatedAt(guestRequestDTO.getCreatedAt());
        guest.setPassword(guestRequestDTO.getPassword());

        guestRepo.save(guest);
    }


}
