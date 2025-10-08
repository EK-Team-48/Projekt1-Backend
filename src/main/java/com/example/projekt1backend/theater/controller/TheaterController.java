package com.example.projekt1backend.theater.controller;

import com.example.projekt1backend.theater.dto.TheaterUpdateRequest;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/theaters/{id}")
    public ResponseEntity<Theater>getTheater(@PathVariable Integer id){
        return new ResponseEntity<>(theaterService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/theaters")
    public ResponseEntity<Theater>createTheater(@RequestBody Theater theater){
        return new ResponseEntity<>(theaterService.createTheater(theater), HttpStatus.CREATED);
    }

    @PutMapping("/theaters/{id}")
    public ResponseEntity<Theater>updateTheater(@PathVariable Integer id, @RequestBody TheaterUpdateRequest dto){
        Theater updatedTheater = theaterService.updateTheaterName(id, dto);
        if(updatedTheater != null){
            return ResponseEntity.ok(updatedTheater);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/theaters/{id}")
    public ResponseEntity<String>deleteTheater(@PathVariable Integer id){
        Theater theater = theaterService.findById(id);
        if(theater != null){
            theaterService.deleteTheater(id);
            return ResponseEntity.ok("Theater deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theater not found");
        }
    }


}
