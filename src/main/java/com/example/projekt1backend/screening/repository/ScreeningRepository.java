package com.example.projekt1backend.screening.repository;


import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    List<Screening> findByMovieAndScreeningDateBetween(Movie movie, LocalDate start, LocalDate end);
}
