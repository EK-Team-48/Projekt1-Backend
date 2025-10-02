package com.example.projekt1backend.Seat.model;

import com.example.projekt1backend.screening.model.Screening;
import com.example.projekt1backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer seatNumber;

    private Integer seatRow;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToMany(mappedBy = "seats", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Screening> screenings = new HashSet<>();


    public Seat(Integer seatNumber, Integer seatRow, Theater theater) {
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.theater = theater;
    }

    public Seat() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

}