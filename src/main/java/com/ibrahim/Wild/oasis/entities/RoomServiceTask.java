package com.ibrahim.Wild.oasis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "RoomServiceTask")
public class RoomServiceTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomServiceTask_id")
    private long roomServiceTaskId;

    @ManyToOne
    @JoinColumn(name = "Room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "Description")
    private String description;
}
