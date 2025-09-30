package com.example.projekt1backend.ageLimit.entity;

import com.example.projekt1backend.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class AgeLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ageLimitId;

    private Integer ageRating;

    @OneToMany(mappedBy = "ageLimit")
    @JsonBackReference
    private Set<Movie> movies = new HashSet<>();

    public AgeLimit() {}

    public AgeLimit(Integer ageRating, Set<Movie> movies) {
        this.ageRating = ageRating;
        this.movies = movies;
    }

    public Integer getAgeLimitId() {
        return ageLimitId;
    }

    public void setAgeLimitId(Integer ageLimitId) {
        this.ageLimitId = ageLimitId;
    }

    public Integer getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(Integer ageRating) {
        this.ageRating = ageRating;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
