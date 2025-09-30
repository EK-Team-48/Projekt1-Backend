package com.example.projekt1backend.genre;

import com.example.projekt1backend.movie.Movie;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    @Column(nullable = false, unique = true)
    private String genre;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    public Genre() {}

    public Genre(String genre, Set<Movie> movies) {
        this.genre = genre;
        this.movies = movies;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
