package com.example.projekt1backend.theater.dto;


public record TheaterDTO(Integer theaterId, String theaterName) {
    public String getTheaterName() {
        return theaterName;
    }
}
