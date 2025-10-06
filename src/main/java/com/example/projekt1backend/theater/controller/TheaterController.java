package com.example.projekt1backend.theater.controller;

import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService){
        this.theaterService = theaterService;
    }

    @GetMapping("/theaters")
    public ResponseEntity<List<Theater>> getAllTheaters(){
        return new ResponseEntity<>(theaterService.getAllTheaters(), HttpStatus.OK);
    }
}
