package com.example.projekt1backend.seat.model;

import java.util.List;

public record BookingRequestDTO (Integer screeningId, List<Integer> seatIds) {

}
