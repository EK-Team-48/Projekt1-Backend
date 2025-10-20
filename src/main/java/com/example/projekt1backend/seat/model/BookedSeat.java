// Java: BookedSeat.java (The Entity)
package com.example.projekt1backend.seat.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "booked_seats")
public class BookedSeat {

    @EmbeddedId
    private BookedSeatId id;


    public BookedSeat() {
        this.id = new BookedSeatId();
    }

    public BookedSeatId getId() {
        return id;
    }

    public void setId(BookedSeatId id) {
        this.id = id;
    }

    public Integer getScreeningId() {
        return this.id.getScreeningId();
    }

    public void setScreeningId(Integer screeningId) {
        this.id.setScreeningId(screeningId);
    }

    public Integer getSeatId() {
        return this.id.getSeatId();
    }

    public void setSeatId(Integer seatId) {
        this.id.setSeatId(seatId);
    }
}