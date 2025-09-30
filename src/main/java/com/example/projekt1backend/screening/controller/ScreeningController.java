package com.example.projekt1backend.screening.controller;


import com.example.projekt1backend.movie.Movie;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/screenings")
public class ScreeningController {

    @Autowired
    ScreeningService screeningService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Screening>> getAllScreenings(){
        return new ResponseEntity<>(screeningService.findAllScreenings(), HttpStatus.OK);
    }


    @GetMapping("/{movieId}")
    public ResponseEntity<List<Screening>>getScreeningsByMovieId(@PathVariable Movie movieId){
        return new ResponseEntity<>(screeningService.findScreeningByMovieId(movieId), HttpStatus.OK);
    }

    @PostMapping("/addScreening")
    public ResponseEntity<Screening>createScreening(@RequestBody Screening screening){
        return new ResponseEntity<>(screeningService.addScreening(screening), HttpStatus.CREATED);
    }


}
