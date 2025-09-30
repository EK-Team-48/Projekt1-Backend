package com.example.projekt1backend.Seat;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    private Boolean name;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seatId;

}
