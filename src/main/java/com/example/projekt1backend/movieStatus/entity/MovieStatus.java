package com.example.projekt1backend.movieStatus.entity;

import com.example.projekt1backend.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MovieStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieStatusId;

    private String status;

    public MovieStatus() {}

    public MovieStatus(String status) {
        this.status = status;
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

}
