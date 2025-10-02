package com.example.projekt1backend.screening.service;



import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScreeningService {


    @Autowired
    ScreeningRepository screeningRepository;

    public List<Screening>findAllScreenings(){
        return screeningRepository.findAll();
    }

    public Screening addScreening(Screening screening){
        return screeningRepository.save(screening);
    }

    public Screening findById(Integer id) {
        return screeningRepository.findById(id).orElseThrow(() -> new RuntimeException("screening not found"));
    }

    public List<Screening> getScreeningsForMovieNextWeek(Movie movie) {
        LocalDate today = LocalDate.now();
        LocalDate weekAhead = today.plusWeeks(1);

        return screeningRepository.findByMovieAndScreeningDateBetween(movie, today, weekAhead);
    }

}
