package com.example.projekt1backend.screening.model;
import com.example.projekt1backend.movie.entity.Movie;
import com.example.projekt1backend.theater.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Screening {

    @Id
    @GeneratedValue
    private Integer screeningId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theaterId;

    private Integer startTime;

    private Double price;


    public Screening() {
    }

    public Screening(Integer screeningId, Movie movieId, Integer startTime, Theater theaterId, Double price) {
        this.screeningId = screeningId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.theaterId = theaterId;
        this.price = price;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Theater getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Theater theaterId) {
        this.theaterId = theaterId;
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
