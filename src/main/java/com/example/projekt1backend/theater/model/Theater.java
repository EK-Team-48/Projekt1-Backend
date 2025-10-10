package com.example.projekt1backend.theater.model;

import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    @Column(nullable = false)
    private String theaterName;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("theater-seats")
    private List<Seat> seatList;


    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Screening> screenings;


    public Theater(String theaterName, List<Seat> seatList) {

        this.theaterName = theaterName;
        this.seatList = seatList;
    }
    public Theater(String theaterName){
        this.theaterName = theaterName;
    }

    public Theater() {}

    public Integer getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Integer id) {
        this.theaterId = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }
}