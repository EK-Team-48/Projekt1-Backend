package com.example.projekt1backend.theater.model;

import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String theaterName;


    @JsonBackReference
    @OneToMany(mappedBy = "theater")
    private List<Seat> seatList;

    public Theater(String theaterName, List<Seat> seatList) {
        this.theaterName = theaterName;
        this.seatList = seatList;
    }
    public Theater(String theaterName){
        this.theaterName = theaterName;
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

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}