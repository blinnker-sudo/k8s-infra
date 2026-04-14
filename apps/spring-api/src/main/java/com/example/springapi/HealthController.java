package com.example.springapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @Value("${DB_HOST:localhost}")
    private String dbHost;

    @Value("${DB_NAME:mydb}")
    private String dbName;

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "ok",
            "service", "spring-api",
            "dbHost", dbHost,
            "dbName", dbName
        );
    }
}
