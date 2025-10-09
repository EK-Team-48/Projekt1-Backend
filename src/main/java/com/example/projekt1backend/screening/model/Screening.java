package com.example.projekt1backend.screening.model;
import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Screening {


    @Id
    @GeneratedValue
    private Integer screeningId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    @JsonBackReference("theater-screenings")
    private Theater theater;

    private LocalDate screeningDate;

    private Integer startTime;

    private Double price;

    @ManyToMany
    @JoinTable(
            name = "booked_seats",
            joinColumns = @JoinColumn(name = "screening_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"screening_id","seat_id"})
    )
    @JsonManagedReference("screening-seats")
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("screening-reservations")
    private List<Reservation> reservations;


    public Screening() {
    }

    public Screening(Integer screeningId, Movie movie, Integer startTime, Theater theater, Double price, LocalDate screeningDate) {
        this.screeningId = screeningId;
        this.movie = movie;
        this.startTime = startTime;
        this.theater = theater;
        this.price = price;
        this.screeningDate = screeningDate;
    }

    public Screening(Movie movie, Theater theater, LocalDate screeningDate, Integer startTime, Double price) {
        this.movie = movie;
        this.theater = theater;
        this.screeningDate = screeningDate;
        this.startTime = startTime;
        this.price = price;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate date) {
        this.screeningDate = date;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
