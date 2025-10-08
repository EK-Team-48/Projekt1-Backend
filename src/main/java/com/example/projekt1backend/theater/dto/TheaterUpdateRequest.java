package com.example.projekt1backend.theater.dto;


public record TheaterUpdateRequest(Integer theaterId, String theaterName) {
    public String getTheaterName() {
        return theaterName;
    }
}
