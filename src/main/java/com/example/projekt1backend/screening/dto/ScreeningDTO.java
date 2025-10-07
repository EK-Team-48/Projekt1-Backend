package com.example.projekt1backend.screening.dto;

import java.time.LocalDate;

public record ScreeningDTO(
        Integer movieId,
        Integer theaterId,
        LocalDate screeningDate,
        Integer startTime,
        Double price
) {
}
