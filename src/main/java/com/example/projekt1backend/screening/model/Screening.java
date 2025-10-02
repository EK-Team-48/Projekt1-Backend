package com.example.projekt1backend.screening.model;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.theater.model.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Screening {


    @Id
    @GeneratedValue
    private Integer screeningId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private LocalDate screeningDate;

    private Integer startTime;

    private Double price;


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
}
