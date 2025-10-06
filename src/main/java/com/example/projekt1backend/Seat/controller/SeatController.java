package com.example.projekt1backend.Seat.controller;

import com.example.projekt1backend.Seat.model.BookedSeat;
import com.example.projekt1backend.Seat.model.BookingRequestDTO;
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
@RequestMapping("/api/v1")
public class SeatController {

    private BookedSeatRepository bookedSeatRepository;
    private SeatRepository seatRepository;

    public SeatController(BookedSeatRepository bookedSeatRepository, SeatRepository seatRepository) {
        this.bookedSeatRepository = bookedSeatRepository;
        this.seatRepository = seatRepository;
    }


    @GetMapping("/bookedseats/{screeningId}")
    public List<BookedSeat> getAllBookedSeatsByScreeningId(@PathVariable Integer screeningId) {
        return bookedSeatRepository.findBookedSeatsById_ScreeningId(screeningId);
    }

    @PostMapping("/bookedseats")
    public ResponseEntity<String>createBookedSeat(@RequestBody BookingRequestDTO bookedSeat){
        Integer screeningId = bookedSeat.screeningId();
        List<Integer> seatIds = bookedSeat.seatIds();

        for(Integer id : seatIds) {
            BookedSeat newBooking = new BookedSeat();

            newBooking.setScreeningId(screeningId);
            newBooking.setSeatId(id);
            bookedSeatRepository.save(newBooking);
        }

        return ResponseEntity.ok("Successfully booked " + seatIds.size() + " seats.");
    }

    @PostMapping("/seats")
    public ResponseEntity<Seat> createNewSeat(@RequestBody Seat seat) {
        return new ResponseEntity<>(seatRepository.save(seat), HttpStatus.CREATED);
    }

    @GetMapping("/seats/{theaterId}")
        public ResponseEntity<List<Seat>> getSeatsByTheaterId(@PathVariable Integer theaterId) {
        return new ResponseEntity<>(seatRepository.findAllByTheater_Id(theaterId), HttpStatus.OK);
        }



}
