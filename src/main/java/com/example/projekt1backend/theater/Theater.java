package com.example.projekt1backend.theater;

import com.example.projekt1backend.Seat.Seat;
import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "theaterId")
    private List<Screening> screeningList;

    @JsonManagedReference
    @OneToMany(mappedBy = "theaterId")
    private List<Seat> seatList;
}