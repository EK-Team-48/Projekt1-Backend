package com.example.projekt1backend.seat.controller;

import com.example.projekt1backend.seat.model.BookedSeat;
import com.example.projekt1backend.seat.model.BookingRequestDTO;
import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.seat.repository.BookedSeatRepository;
import com.example.projekt1backend.seat.repository.SeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<BookedSeat> getAllBookedSeatsByScreeningId(@PathVariable("screeningId") Integer screeningId) {
        return bookedSeatRepository.findBookedSeatsById_ScreeningId(screeningId);
    }

    @PostMapping("/bookedseats")
    public ResponseEntity<?>createBookedSeat(@RequestBody BookingRequestDTO bookedSeat){
        Integer screeningId = bookedSeat.screeningId();
        List<Integer> seatIds = bookedSeat.seatIds();

        for(Integer id : seatIds) {
            BookedSeat newBooking = new BookedSeat();

            newBooking.setScreeningId(screeningId);
            newBooking.setSeatId(id);
            bookedSeatRepository.save(newBooking);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "Successfully booked seats.",
                        "seatsBookedCount", seatIds.size(),
                        "screeningId", screeningId
                )
        );
    }

    @PostMapping("/seats")
    public ResponseEntity<Seat> createNewSeat(@RequestBody Seat seat) {
        return new ResponseEntity<>(seatRepository.save(seat), HttpStatus.CREATED);
    }

    @GetMapping("/seats/{theaterId}")
        public ResponseEntity<List<Seat>> getSeatsByTheaterId(@PathVariable("theaterId") Integer theaterId) {
        return new ResponseEntity<>(seatRepository.findAllByTheater_TheaterId(theaterId), HttpStatus.OK);
        }



}
