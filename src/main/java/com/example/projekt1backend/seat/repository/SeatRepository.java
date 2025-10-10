package com.example.projekt1backend.seat.repository;

import com.example.projekt1backend.seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByTheater_TheaterId(Integer theaterId);

}
