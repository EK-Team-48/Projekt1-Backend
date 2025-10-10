package com.example.projekt1backend.reservation.entity;

import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.customer.entity.Customer;
import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name ="screening_id")
    @JsonBackReference("screening-reservations")
    private Screening screening;

    @ManyToMany
    @JoinTable(
            name = "reservation_seats",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    @JsonManagedReference("seat-reservations")
    private List<Seat> seats;


    private String userReservationId;


    public Reservation() {
    }

    public Reservation(Integer reservationId, Customer customer, Screening screening, String userReservationId) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.screening = screening;
        this.userReservationId = userReservationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getUserReservationId() {
        return userReservationId;
    }

    public void setUserReservationId(String userReservationId) {
        this.userReservationId = userReservationId;
    }

}
