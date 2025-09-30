package com.example.projekt1backend.Seat;

import com.example.projekt1backend.theater.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer seatNumber;

    private Integer seatRow;

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "statusId")
    private Status seatStatus;


    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theaterId;


    public Seat(Integer seatNumber, Integer seatRow, Theater theaterId, Status seatStatus) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.theaterId = theaterId;
        this.seatStatus = seatStatus;
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

    public Theater getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Theater theaterId) {
        this.theaterId = theaterId;
    }


}