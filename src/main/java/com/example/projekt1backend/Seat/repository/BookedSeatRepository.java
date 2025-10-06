package com.example.projekt1backend.Seat.repository;

import com.example.projekt1backend.Seat.model.BookedSeat;
import com.example.projekt1backend.Seat.model.BookedSeatId;
import com.example.projekt1backend.Seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, BookedSeatId> {

    List<BookedSeat> findBookedSeatsById_ScreeningId(Integer screeningId);
}
