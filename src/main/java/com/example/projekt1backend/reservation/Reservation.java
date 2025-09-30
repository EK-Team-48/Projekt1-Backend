package com.example.projekt1backend.reservation;

import com.example.projekt1backend.customer.Customer;
import com.example.projekt1backend.screening.model.Screening;
import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name ="screening_id")
    private Screening screening;
}
