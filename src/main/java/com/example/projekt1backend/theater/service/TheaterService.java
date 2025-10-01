package com.example.projekt1backend.theater.service;


import com.example.projekt1backend.theater.model.Theater;
import com.example.projekt1backend.theater.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public Theater findById(Integer id){
        return theaterRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }



}
