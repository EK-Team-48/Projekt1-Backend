package com.example.projekt1backend.reservation.dto;

import java.time.LocalDate;
import java.util.List;

public record ReservationViewDTO(
        String firstName,
        String lastName,
        String phoneNumber,
        String movieTitle,
        LocalDate screeningDate,
        String reservationID,
        List<SeatDTO> seats
) {}
