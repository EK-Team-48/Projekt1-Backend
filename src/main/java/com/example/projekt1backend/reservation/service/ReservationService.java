package com.example.projekt1backend.reservation.service;

import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.reservation.dto.ReservationViewDTO;
import com.example.projekt1backend.reservation.dto.SeatDTO;
import com.example.projekt1backend.reservation.entity.Reservation;
import com.example.projekt1backend.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Reservation findByLastFour(String userReservationId) {
        return reservationRepository.findByLastFour(userReservationId);
    }

    public ReservationViewDTO findByLastFourDTO(String lastFour) {
        Reservation find = reservationRepository.findByLastFour(lastFour);

        if (find == null) {
            throw new EntityNotFoundException("Reservation not found for last four: " + lastFour);
        }


        return new ReservationViewDTO(
                find.getCustomer().getFirstName(),
                find.getCustomer().getLastName(),
                find.getCustomer().getNumber(),
                find.getScreening().getMovie().getMovieTitle(),
                find.getScreening().getScreeningDate(),
                find.getSeats().stream()
                        .map(seat -> new SeatDTO(seat.getSeatRow(), seat.getSeatNumber()))
                        .toList()
        );
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

    public void deleteById(Integer deleteId) {
        reservationRepository.deleteById(deleteId);
    }

    public Optional<Reservation> findByIdOptional(Integer id) {
        return reservationRepository.findById(id);
    }
}
