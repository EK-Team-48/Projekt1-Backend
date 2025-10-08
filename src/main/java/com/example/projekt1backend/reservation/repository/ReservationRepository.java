package com.example.projekt1backend.reservation.repository;

import com.example.projekt1backend.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE RIGHT(user_reservation_id, 4) = :lastFour", nativeQuery = true)
    Reservation findByLastFour(@Param("lastFour") String lastFour);

}
