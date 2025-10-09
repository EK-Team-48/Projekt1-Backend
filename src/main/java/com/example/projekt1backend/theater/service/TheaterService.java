package com.example.projekt1backend.theater.service;


import com.example.projekt1backend.Seat.model.Seat;
import com.example.projekt1backend.theater.dto.TheaterCreationRequest;
import com.example.projekt1backend.theater.dto.TheaterUpdateRequest;
import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public Theater findById(Integer id){
        return theaterRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Theater updateTheaterName(Integer id, TheaterUpdateRequest dto) {
        Theater orgTheater = theaterRepository.findById(id).orElse(null);

        if (orgTheater == null) {
            return null;
        }

        orgTheater.setTheaterName(dto.getTheaterName());

        return theaterRepository.save(orgTheater);
    }

    public List<Theater>getAllTheaters(){
        return theaterRepository.findAll();
    }

    public Theater createTheaterFromRequest(TheaterCreationRequest request) {
        Theater newTheater = new Theater();
        newTheater.setTheaterName(request.theaterName());

        newTheater.setSeatList(new java.util.ArrayList<>());

        for (int row = 1; row <= request.numberOfRows(); row++) {
            for (int seatNum = 1; seatNum <= request.seatsPerRow(); seatNum++) {
                Seat seat = new Seat(seatNum, row, newTheater);
                newTheater.getSeatList().add(seat);
            }
        }

        return theaterRepository.save(newTheater);
    }
    public void deleteTheater(Integer id){
        theaterRepository.deleteById(id);
    }



}
