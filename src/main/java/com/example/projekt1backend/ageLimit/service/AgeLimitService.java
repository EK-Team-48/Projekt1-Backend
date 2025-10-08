package com.example.projekt1backend.ageLimit.service;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.repository.AgeLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgeLimitService {

    private final AgeLimitRepository ageLimitRepository;

    public AgeLimitService(AgeLimitRepository ageLimitRepository) {
        this.ageLimitRepository = ageLimitRepository;
    }

    public List<AgeLimit> findAll() {
        return ageLimitRepository.findAll();
    }

    public Optional<AgeLimit> findById(Integer id) {
        return ageLimitRepository.findById(id);
    }

    public AgeLimit save(AgeLimit ageLimit) {
        return ageLimitRepository.save(ageLimit);
    }

    public void deleteById(Integer id) {
        ageLimitRepository.deleteById(id);
    }
}
