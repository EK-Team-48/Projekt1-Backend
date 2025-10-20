package com.example.projekt1backend.ticket;

import com.example.projekt1backend.seat.model.Seat;
import com.example.projekt1backend.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservationId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seatId;

}
