package com.ibrahim.Wild.oasis.controllers;

import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.EmployeeDTOS.EmployeeResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskResponseDTO;
import com.ibrahim.Wild.oasis.services.RoomServiceTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtasks")
@Validated
@RequiredArgsConstructor
public class RoomServiceTaskController {
    private final RoomServiceTaskService roomServiceTaskService;

    @GetMapping
    public ResponseEntity<List<RoomServiceTaskResponseDTO>> getAllRoomTasks() {
        List<RoomServiceTaskResponseDTO> roomTasks = roomServiceTaskService.getAllRoomTasks();
        return ResponseEntity.ok(roomTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomServiceTaskResponseDTO> getRoomTaskById(@PathVariable long id) {
        return roomServiceTaskService.getRoomTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addRoomTask(@RequestBody @Valid RoomServiceTaskRequestDTO roomServiceTaskRequestDTO) {
        roomServiceTaskService.addRoomTask(roomServiceTaskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRoomTask(@PathVariable long id, @RequestBody @Valid RoomServiceTaskRequestDTO roomServiceTaskRequestDTO) {
        roomServiceTaskService.updateRoomTask(roomServiceTaskRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomTask(@PathVariable long id) {
        roomServiceTaskService.deleteRoomTask(id);
        return ResponseEntity.noContent().build();
    }
}
