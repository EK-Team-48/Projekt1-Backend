package com.example.projekt1backend.reservation.controller;


import com.example.projekt1backend.customer.Customer;
import com.example.projekt1backend.customer.CustomerService;
import com.example.projekt1backend.reservation.dto.ReservationDTO;
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

    public ReservationController(ReservationService reservationService, CustomerService customerService, ScreeningService screeningService) {
        this.reservationService = reservationService;
        this.customerService = customerService;
        this.screeningService = screeningService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAll() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) {

        Customer customer = customerService.findById(reservationDTO.customerID());
        Screening screening = screeningService.findById(reservationDTO.screeningID());
        Reservation reservation = new Reservation();

        reservation.setCustomer(customer);
        reservation.setScreening(screening);
        reservationService.addReservation(reservation);
        return new ResponseEntity<>("Reservation has been created" , HttpStatus.CREATED);
    }
}
