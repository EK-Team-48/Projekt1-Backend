package com.example.projekt1backend.movie.repository;

import com.example.projekt1backend.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
