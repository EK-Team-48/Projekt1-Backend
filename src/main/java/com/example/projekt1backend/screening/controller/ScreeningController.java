package com.example.projekt1backend.screening.controller;


import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final MovieService movieService;

    public ScreeningController(ScreeningService screeningService, MovieService movieService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
    }

    @GetMapping("/screenings")
    public ResponseEntity<List<Screening>> getAllScreenings(){
        return new ResponseEntity<>(screeningService.findAllScreenings(), HttpStatus.OK);
    }


    @GetMapping("/screenings/{id}")
    public ResponseEntity<List<Screening>>getScreeningsByMovie(@PathVariable("id") Integer id){
        Movie movie_id = movieService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return new ResponseEntity<>(screeningService.getScreeningsForMovieNextWeek(movie_id), HttpStatus.OK);
    }

    @PostMapping("/screenings")
    public ResponseEntity<Screening>createScreening(@RequestBody Screening screening){
        return new ResponseEntity<>(screeningService.addScreening(screening), HttpStatus.CREATED);
    }


}
