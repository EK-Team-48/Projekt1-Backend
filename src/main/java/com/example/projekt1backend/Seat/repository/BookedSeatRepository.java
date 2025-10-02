package com.example.projekt1backend.Seat.repository;

import com.example.projekt1backend.Seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookedSeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByScreenings_ScreeningId(Integer screeningId);
}
