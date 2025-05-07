package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_id")
    private long roomId;

    @Column(name = "Description")
    private String description;
    @Column(name = "Image")
    private String image;
    @Column(name = "Room_price")
    private int roomPrice;

}
