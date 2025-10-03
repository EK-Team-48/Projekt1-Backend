package com.example.projekt1backend.Seat.repository;

import com.example.projekt1backend.Seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByTheater_Id(Integer theaterId);

}
