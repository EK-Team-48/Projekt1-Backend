package com.example.projekt1backend.theater;

import com.example.projekt1backend.Seat.Seat;
import com.example.projekt1backend.screening.model.Screening;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String theaterName;

    @OneToMany(mappedBy = "theaterId")
    private List<Screening> screeningList;

    @OneToMany(mappedBy = "theaterId")
    private List<Seat> seatList;
}