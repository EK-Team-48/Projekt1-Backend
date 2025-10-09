package com.example.projekt1backend.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/healthz")
    @ResponseBody
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "service", "my-application"
        );
    }
}