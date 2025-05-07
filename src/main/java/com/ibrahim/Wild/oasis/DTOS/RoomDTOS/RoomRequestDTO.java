package com.ibrahim.Wild.oasis.DTOS.RoomDTOS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequestDTO {

    @NotBlank
    private String description;

    @NotBlank
    private String image;

    @Min(1)
    private int roomPrice;
}

