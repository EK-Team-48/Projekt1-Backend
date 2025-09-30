package com.example.projekt1backend.movieStatus;

import com.example.projekt1backend.movie.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MovieStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieStatusId;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieStatus")
    @JsonBackReference
    private Set<Movie> movies;

    public MovieStatus() {}

    public MovieStatus(String status, Set<Movie> movies) {
        this.status = status;
        this.movies = movies;
    }

    public Integer getMovieStatusId() {
        return movieStatusId;
    }

    public void setMovieStatusId(Integer movieStatusId) {
        this.movieStatusId = movieStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
