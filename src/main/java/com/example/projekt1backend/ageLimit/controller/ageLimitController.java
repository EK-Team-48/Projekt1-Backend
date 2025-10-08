package com.example.projekt1backend.ageLimit.controller;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.service.AgeLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ageLimitController {

    @Autowired
    AgeLimitService ageLimitService;

    @GetMapping("/ageLimits")
    public ResponseEntity<List<AgeLimit>> getAll() {
        return new ResponseEntity<>(ageLimitService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ageLimits/{id}")
    public ResponseEntity<AgeLimit> getAgeLimitById(@PathVariable Integer id) {
        return new ResponseEntity<>(ageLimitService.findById(id).orElseThrow(() -> new RuntimeException("Age Limit not found with id: " + id)),
                HttpStatus.OK);
    }

    @PostMapping("/ageLimits")
    public ResponseEntity<AgeLimit> postAgeLimit(@RequestBody AgeLimit ageLimit) {
        return new ResponseEntity<>(ageLimitService.save(ageLimit), HttpStatus.CREATED);
    }

    @DeleteMapping("ageLimits/{id}")
    public ResponseEntity<String> deleteAgeLimit(@PathVariable Integer id) {
        Optional<AgeLimit> orgAgeLimit = ageLimitService.findById(id);
        if (orgAgeLimit.isPresent()) {
            Integer ageLimit = orgAgeLimit.get().getAgeRating();
            ageLimitService.deleteById(orgAgeLimit.get().getAgeLimitId());
            return ResponseEntity.ok(ageLimit + ": Has been removed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Age Limit not found");
        }
    }
}
