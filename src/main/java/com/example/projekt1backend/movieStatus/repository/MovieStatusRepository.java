package com.example.projekt1backend.movieStatus.repository;

import com.example.projekt1backend.movieStatus.entity.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStatusRepository extends JpaRepository<MovieStatus, Integer> {
}
