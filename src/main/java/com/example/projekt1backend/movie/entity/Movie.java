package com.example.projekt1backend.movie.entity;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.genre.entity.Genre;

import com.example.projekt1backend.screening.model.Screening;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false)
    private String movieImg;

    @Column(nullable = false)
    private String movieTitle;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private String trailerLink;

    @ManyToOne
    @JoinColumn(name = "age_limit_id", nullable = false)
    private AgeLimit ageLimit;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id","genre_id"})
    )
    private Set<Genre> genres = new HashSet<>();


    public Movie() {}

    public Movie(String movieImg, String movieTitle, String description, Integer duration, String trailerLink, AgeLimit ageLimit, Set<Genre> genres) {
        this.movieImg = movieImg;
        this.movieTitle = movieTitle;
        this.description = description;
        this.duration = duration;
        this.trailerLink = trailerLink;
        this.ageLimit = ageLimit;
        this.genres = genres;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public AgeLimit getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(AgeLimit ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
