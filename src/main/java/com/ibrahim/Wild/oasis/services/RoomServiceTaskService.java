package com.ibrahim.Wild.oasis.services;

import com.ibrahim.Wild.oasis.DTOMapper.DTOMapper;
import com.ibrahim.Wild.oasis.DTOS.GuestDTOS.GuestResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskResponseDTO;
import com.ibrahim.Wild.oasis.entities.Employee;
import com.ibrahim.Wild.oasis.entities.RoomServiceTask;
import com.ibrahim.Wild.oasis.entities.Room;
import com.ibrahim.Wild.oasis.exceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.ibrahim.Wild.oasis.repositories.EmployeeRepo;
import com.ibrahim.Wild.oasis.repositories.RoomServiceTaskRepo;
import com.ibrahim.Wild.oasis.repositories.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceTaskService {

    private final DTOMapper dtoMapper;
    private final RoomServiceTaskRepo roomServiceTaskRepo;
    private final RoomRepo roomRepo;
    private final EmployeeRepo employeeRepo;

    public List<RoomServiceTaskResponseDTO> getAllRoomTasks(){
        return roomServiceTaskRepo.findAll().stream().map(dtoMapper::convertToRoomServiceTaskDTO).toList();
    }

    public Optional<RoomServiceTaskResponseDTO> getRoomTaskById(long id){
        Optional<RoomServiceTaskResponseDTO> room = roomServiceTaskRepo.findById(id).map(dtoMapper::convertToRoomServiceTaskDTO);
        if (room.isEmpty()) {
            throw new ResourceNotFoundException(STR."Room with id \{id} not found!");
        }
        return room;    }

    public void addRoomTask(RoomServiceTaskRequestDTO roomServiceTaskRequestDTO){
        Room room = roomRepo
                .findById(roomServiceTaskRequestDTO.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException(STR."Room with id \{roomServiceTaskRequestDTO.getRoomId()} not found!"));

        Employee employee = employeeRepo.findById(roomServiceTaskRequestDTO.getEmployeeId())
                .orElseThrow(()->new ResourceNotFoundException(STR."Employee with id \{roomServiceTaskRequestDTO.getEmployeeId()} not found!"));

        RoomServiceTask roomServiceTask =RoomServiceTask.builder()
                .description(roomServiceTaskRequestDTO.getDescription())
                .room(room)
                .employee(employee)
                .build();
        roomServiceTaskRepo.save(roomServiceTask);
    }

    public void deleteRoomTask(long id){
        Optional<RoomServiceTask> roomServiceTask = roomServiceTaskRepo.findById(id);
        if (roomServiceTask.isEmpty()){
            throw new ResourceNotFoundException(STR."Room service task with id \{id} not found!");
        }
        roomServiceTaskRepo.deleteById(id);
    }

    public void updateRoomTask(RoomServiceTaskRequestDTO roomServiceTaskRequestDTO, long id) {
        RoomServiceTask roomTask = roomServiceTaskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Room task with id \{id} was not found!"));

        Room room = roomRepo
                .findById(roomServiceTaskRequestDTO.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException(STR."Room with id \{roomServiceTaskRequestDTO.getRoomId()} not found!"));

        Employee employee = employeeRepo.findById((roomServiceTaskRequestDTO.getEmployeeId()))
                        .orElseThrow(()-> new ResourceNotFoundException(STR."Employee with id \{roomServiceTaskRequestDTO.getEmployeeId()} not found!"));

        roomTask.setDescription(roomServiceTaskRequestDTO.getDescription());
        roomTask.setRoom(room);
        roomTask.setEmployee(employee);

        roomServiceTaskRepo.save(roomTask);
    }



}
