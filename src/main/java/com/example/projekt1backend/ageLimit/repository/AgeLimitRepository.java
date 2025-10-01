package com.example.projekt1backend.ageLimit.repository;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeLimitRepository extends JpaRepository<AgeLimit, Integer> {
}
