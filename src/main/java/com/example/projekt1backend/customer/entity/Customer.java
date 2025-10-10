package com.example.projekt1backend.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date age;

    @Column(nullable = false)
    private String number;

}
