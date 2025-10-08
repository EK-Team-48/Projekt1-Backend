package com.example.projekt1backend.ageLimit.controller;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.repository.AgeLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ageLimitController {

    @Autowired
    AgeLimitRepository ageLimitRepository;

    @GetMapping("/ageLimits")
    public ResponseEntity<List<AgeLimit>> getAll() {
        return new ResponseEntity<>(ageLimitRepository.findAll(), HttpStatus.OK);
    }
}
