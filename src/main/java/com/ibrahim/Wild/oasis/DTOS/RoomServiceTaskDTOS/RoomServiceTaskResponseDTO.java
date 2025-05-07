package com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomServiceTaskResponseDTO {

    private long roomServiceTaskId;

    private long roomId;

    private long employeeId;

    private String description;
}