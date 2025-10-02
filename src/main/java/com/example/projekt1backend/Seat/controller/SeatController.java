package com.example.projekt1backend.Seat.controller;

import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.Seat.repository.BookedSeatRepository;
import com.example.projekt1backend.Seat.repository.SeatRepository;
import com.example.projekt1backend.theater.model.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SeatController {

    private BookedSeatRepository bookedSeatRepository;
    private SeatRepository seatRepository;

    public SeatController(BookedSeatRepository bookedSeatRepository, SeatRepository seatRepository) {
        this.bookedSeatRepository = bookedSeatRepository;
        this.seatRepository = seatRepository;
    }


    @GetMapping("/seats/{screeningId}")
    public List<Seat> getAllBookedSeatsByScreeningId(@PathVariable Integer screeningId) {
        return bookedSeatRepository.findByScreenings_ScreeningId(screeningId);

    }

    @PostMapping("/seats")
    public ResponseEntity<Seat> createNewSeat(@RequestBody Seat seat) {
        return new ResponseEntity<>(seatRepository.save(seat), HttpStatus.CREATED);
    }

    @GetMapping("/seats")
        public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatRepository.findAll());

        }



}
