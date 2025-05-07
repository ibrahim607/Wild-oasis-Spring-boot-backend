package com.ibrahim.Wild.oasis.controllers;

import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomDTOS.RoomResponseDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskRequestDTO;
import com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS.RoomServiceTaskResponseDTO;
import com.ibrahim.Wild.oasis.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Validated
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> roomTasks = roomService.getAllRooms();
        return ResponseEntity.ok(roomTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable long id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addRoom(@RequestBody @Valid RoomRequestDTO roomRequestDTO) {
        roomService.addRoom(roomRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable long id, @RequestBody @Valid RoomRequestDTO roomRequestDTO) {
        roomService.updateRoom(roomRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
