package com.example.projekt1backend.reservation.service;

import com.example.projekt1backend.reservation.dto.ReservationViewDTO;
import com.example.projekt1backend.reservation.dto.SeatDTO;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationViewDTO> findAllAsDTOs() {
        return reservationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("reservation not found"));
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public ReservationViewDTO convertToDTO(Reservation reservation) {
        return new ReservationViewDTO(
                reservation.getCustomer().getFirstName(),
                reservation.getCustomer().getLastName(),
                reservation.getCustomer().getNumber(),
                reservation.getScreening().getMovie().getMovieTitle(),
                reservation.getScreening().getScreeningDate(),
                reservation.getSeats().stream()
                        .map(seat -> new SeatDTO(seat.getSeatRow(), seat.getSeatNumber()))
                        .toList()
        );
    }
}
