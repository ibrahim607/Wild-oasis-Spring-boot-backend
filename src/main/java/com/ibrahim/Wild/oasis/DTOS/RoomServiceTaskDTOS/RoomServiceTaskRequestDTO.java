package com.ibrahim.Wild.oasis.DTOS.RoomServiceTaskDTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomServiceTaskRequestDTO {

    @NotNull
    private long roomId;

    @NotNull
    private long employeeId;

    @NotBlank
    private String description;
}

