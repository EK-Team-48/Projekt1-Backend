package com.example.projekt1backend.seat.service;

import com.example.projekt1backend.seat.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

}