package com.example.projekt1backend.ageLimit.entity;

import com.example.projekt1backend.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;



import java.util.HashSet;
import java.util.Set;

@Entity
public class AgeLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ageLimitId;

    @Column(nullable = false, unique = true)
    private Integer ageRating;

    public AgeLimit() {}

    public AgeLimit(Integer ageRating) {
        this.ageRating = ageRating;
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

}
