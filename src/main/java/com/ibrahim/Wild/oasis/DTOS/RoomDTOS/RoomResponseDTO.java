package com.ibrahim.Wild.oasis.DTOS.RoomDTOS;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDTO {
    private long roomId;
    private String description;
    private String image;
    private int roomPrice;
}
