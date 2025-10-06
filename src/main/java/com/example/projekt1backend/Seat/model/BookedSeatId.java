// Java: BookedSeatId.java (The Composite Primary Key)
package com.example.projekt1backend.Seat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookedSeatId implements Serializable {

    @Column(name = "screening_id")
    private Integer screeningId;
    @Column(name = "seat_id")
    private Integer seatId;

    // --- Constructors ---
    public BookedSeatId() {}

    public BookedSeatId(Integer screeningId, Integer seatId) {
        this.screeningId = screeningId;
        this.seatId = seatId;
    }

    // --- Getters and Setters (Required) ---
    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    // --- equals() and hashCode() (Crucial for Composite Keys) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedSeatId that = (BookedSeatId) o;
        return Objects.equals(screeningId, that.screeningId) &&
                Objects.equals(seatId, that.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screeningId, seatId);
    }
}