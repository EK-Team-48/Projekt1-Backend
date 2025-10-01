package com.example.projekt1backend.theater.repository;

import com.example.projekt1backend.theater.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
