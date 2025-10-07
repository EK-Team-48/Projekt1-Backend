package com.example.projekt1backend.theater.dto;

import java.util.List;

public record TheaterDTO(
        String theaterName,
        List<Integer> seatIds
) {
}
