package com.example.projekt1backend.screening.controller;


import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.movie.service.MovieService;
import com.example.projekt1backend.screening.dto.ScreeningDTO;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.service.TheaterService;
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
    private final TheaterService theaterService;

    public ScreeningController(ScreeningService screeningService, MovieService movieService, TheaterService theaterService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.theaterService = theaterService;
    }

    @GetMapping("/screenings")
    public ResponseEntity<List<Screening>> getAllScreenings(){
        return new ResponseEntity<>(screeningService.findAllScreenings(), HttpStatus.OK);
    }
    @GetMapping("/screenings/{id}")
    public ResponseEntity<Screening>getScreeningById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(screeningService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/screenings/movie/{id}")
    public ResponseEntity<List<Screening>>getScreeningsByMovie(@PathVariable("id") Integer id){
        Movie movie_id = movieService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return new ResponseEntity<>(screeningService.getScreeningsForMovieNextWeek(movie_id), HttpStatus.OK);
    }

    @PostMapping("/screenings")
    public ResponseEntity<Screening> createScreening(@RequestBody ScreeningDTO dto){
        Movie movie = movieService.findById(dto.movieId()).orElseThrow();
        Theater theater = theaterService.findById(dto.theaterId());

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setTheater(theater);
        screening.setScreeningDate(dto.screeningDate());
        screening.setStartTime(dto.startTime());
        screening.setPrice(dto.price());

        return new ResponseEntity<>(screeningService.addScreening(screening), HttpStatus.CREATED);
    }

    @DeleteMapping("/screenings/{id}")
    public ResponseEntity<String>deleteScreening(@PathVariable("id") Integer id){
        Screening screening = screeningService.findById(id);
        if(screening != null){
            screeningService.deletedScreening(screening.getScreeningId());
            return ResponseEntity.ok("screening has been removed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Screening not found");
        }
    }

    @PutMapping("/screenings/{id}")
    public ResponseEntity<Screening>updateScreening(@PathVariable("id") Integer id, @RequestBody ScreeningDTO dto){
        Screening src = screeningService.findById(id);
        if(src != null && dto != null){
            Movie movie = movieService.findById(dto.movieId()).orElseThrow();
            Theater theater = theaterService.findById(dto.theaterId());
            Screening screening = new Screening();
            screening.setScreeningId(id);
            screening.setMovie(movie);
            screening.setTheater(theater);
            screening.setScreeningDate(dto.screeningDate());
            screening.setStartTime(dto.startTime());
            screening.setPrice(dto.price());
            return new ResponseEntity<>(screeningService.addScreening(screening), HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
