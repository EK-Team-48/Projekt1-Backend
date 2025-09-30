package com.example.projekt1backend.genre.repository;

import com.example.projekt1backend.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
