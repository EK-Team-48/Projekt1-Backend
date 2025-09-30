package com.example.projekt1backend.screening;

import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.theater.Theater;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Screening {

    @Id
    @GeneratedValue
    private Integer screningId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theaterId;

    private Integer startTime;

    private Double price;


}
