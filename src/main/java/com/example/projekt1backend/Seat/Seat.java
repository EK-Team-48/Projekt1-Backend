package com.example.projekt1backend.Seat;

import com.example.projekt1backend.theater.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer seatNumber;

    private char seatRow;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theaterId;

    @OneToMany(mappedBy = "seatId")
    private List<Status> seatStatus;
}