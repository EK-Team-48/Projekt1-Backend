package com.example.projekt1backend.reservation.dto;

import java.util.List;

public record ReservationDTO(Integer customerID, Integer screeningID, List<Integer> seatId) {
}
