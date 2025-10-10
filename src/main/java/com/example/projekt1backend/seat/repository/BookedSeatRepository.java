package com.example.projekt1backend.seat.repository;

import com.example.projekt1backend.seat.model.BookedSeat;
import com.example.projekt1backend.seat.model.BookedSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, BookedSeatId> {

    List<BookedSeat> findBookedSeatsById_ScreeningId(Integer screeningId);
}
