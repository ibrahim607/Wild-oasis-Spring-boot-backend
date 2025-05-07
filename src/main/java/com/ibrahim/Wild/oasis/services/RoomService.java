package com.ibrahim.Wild.oasis.services;

import com.ibrahim.Wild.oasis.DTOMapper.DTOMapper;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomResponseDTO;
import com.ibrahim.Wild.oasis.entities.Guest;
import com.ibrahim.Wild.oasis.entities.Room;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepo roomRepo;
    private final DTOMapper dtoMapper;

    public List<RoomResponseDTO> getAllRooms(){
        return roomRepo.findAll().stream().map(dtoMapper::convertToRoomDTO).toList();
    }

    public Optional<RoomResponseDTO> getRoomById (long id){
        Optional<Room> room = roomRepo.findById(id);
        if (room.isEmpty()){
            throw new ResourceNotFoundException(STR."Room with id \{id} not found!");
        }
        return roomRepo.findById(id).map(dtoMapper::convertToRoomDTO);
    }

    public void addRoom(RoomRequestDTO roomRequestDTO){
        Room room = dtoMapper.convertToRoom(roomRequestDTO);
        roomRepo.save(room);
    }

    public void deleteRoom(long id){
        Optional<Room> room = roomRepo.findById(id);
        if (room.isEmpty()){
            throw new ResourceNotFoundException(STR."Room with id \{id} not found!");
        }
        roomRepo.deleteById(id);
    }

    public void updateRoom(RoomRequestDTO roomRequestDTO , long id){
        Room room = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Room with id \{id} not found!"));

        room.setRoomPrice(roomRequestDTO.getRoomPrice());
        room.setDescription(roomRequestDTO.getDescription());
        room.setImage(roomRequestDTO.getImage());

        roomRepo.save(room);
    }
}
