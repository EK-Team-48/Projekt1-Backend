package com.example.projekt1backend.theater.dto;

public record TheaterCreationRequest(
        String theaterName,
        Integer numberOfRows,
        Integer seatsPerRow
) {

}
