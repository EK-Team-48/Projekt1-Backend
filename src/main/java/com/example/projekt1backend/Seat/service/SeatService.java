package com.example.projekt1backend.Seat.service;

import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.Seat.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

}