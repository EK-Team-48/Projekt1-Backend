package com.example.projekt1backend.reservation.controller;


import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.Seat.repository.SeatRepository;
import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.customer.service.CustomerService;
import com.example.projekt1backend.reservation.dto.ReservationDTO;
import com.example.projekt1backend.reservation.dto.ReservationViewDTO;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.screening.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final ScreeningService screeningService;
    private final SeatRepository seatRepository;

    public ReservationController(ReservationService reservationService, CustomerService customerService, ScreeningService screeningService, SeatRepository seatRepository) {
        this.reservationService = reservationService;
        this.customerService = customerService;
        this.screeningService = screeningService;
        this.seatRepository = seatRepository;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationViewDTO>> getAll() {
        return ResponseEntity.ok(reservationService.findAllAsDTOs());
    }

    @GetMapping("/reservations/{userId}")
    public ResponseEntity<?> getById(@PathVariable("userId") String userId) {
        try {
            if (userId.length() != 4) {
                return ResponseEntity.badRequest().body("Please provide the last four characters of your reservation ID");
            }
            return new ResponseEntity<>(reservationService.findByLastFourDTO(userId), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Bad request, control user id", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {

        Customer customer = customerService.findById(reservationDTO.customerID());
        Screening screening = screeningService.findById(reservationDTO.screeningID());
        List<Seat> seats = seatRepository.findAllById(reservationDTO.seatId());

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setScreening(screening);
        reservation.setSeats(seats);
        reservation.setUserReservationId(reservationDTO.userReservationId());

        reservationService.addReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created");
    }
}
