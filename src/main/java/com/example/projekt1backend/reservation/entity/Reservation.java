package com.example.projekt1backend.reservation.entity;

import com.example.projekt1backend.customer.Customer;
import com.example.projekt1backend.screening.model.Screening;
import jakarta.persistence.*;
import lombok.*;



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
    private Screening screening;


    public Reservation() {
    }

    public Reservation(Integer reservationId, Customer customer, Screening screening) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.screening = screening;
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
}
