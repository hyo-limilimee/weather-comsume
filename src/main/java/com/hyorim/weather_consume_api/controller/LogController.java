package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.service.LogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @PostMapping
    public AnalysisLog saveLog(@RequestBody LogRequest request) {
        return logService.saveLog(request.getInputData(), request.getResult());
    }

    @GetMapping
    public List<AnalysisLog> getAllLogs() {
        return logService.getAllLogs();
    }

    @Getter
    @Setter
    static class LogRequest {
        private String inputData;
        private String result;
    }
}
