package com.example.projekt1backend.reservation.controller;


import com.example.projekt1backend.customer.Customer;
import com.example.projekt1backend.customer.CustomerService;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;

    public ReservationController(ReservationService reservationService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.customerService = customerService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Reservation>> getAll() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping("/addreservation")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        //Tester lige nu med en bruger jeg selv opretter for at se om det virker som det skal i backend.
        //Hvilke det gør, skal bare lige finde ud af hvordan jeg får id fra brugeren med.
        //Plus det skal være i service klassen det her, ikke i controller. To be continued....
        Customer customer = customerService.findById(1);
        reservation.setCustomer(customer);
        reservationService.addReservation(reservation);
        return new ResponseEntity<>(reservation + ": has been created", HttpStatus.CREATED);
    }


}
