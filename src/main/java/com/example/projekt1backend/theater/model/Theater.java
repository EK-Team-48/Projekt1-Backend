package com.example.projekt1backend.theater.model;

import com.example.projekt1backend.Seat.Seat;
import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String theaterName;

    @JsonManagedReference
    @OneToMany(mappedBy = "theaterId")
    private List<Screening> screeningList;

    @JsonManagedReference
    @OneToMany(mappedBy = "theaterId")
    private List<Seat> seatList;

    public Theater(Integer id, String theaterName, List<Screening> screeningList, List<Seat> seatList) {
        this.id = id;
        this.theaterName = theaterName;
        this.screeningList = screeningList;
        this.seatList = seatList;
    }

    public Theater() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public List<Screening> getScreeningList() {
        return screeningList;
    }

    public void setScreeningList(List<Screening> screeningList) {
        this.screeningList = screeningList;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}