package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final AnalysisLogRepository logRepository;

    @GetMapping
    public List<AnalysisLog> getLogs() {
        return logRepository.findAll();
    }
}
