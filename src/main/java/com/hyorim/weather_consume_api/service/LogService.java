package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final AnalysisLogRepository logRepository;

    public AnalysisLog saveLog(String inputData, String result) {
        AnalysisLog log = AnalysisLog.builder()
                .requestTime(LocalDateTime.now())
                .inputData(inputData)
                .predictionResult(result)
                .build();
        return logRepository.save(log);
    }

    public List<AnalysisLog> getAllLogs() {
        return logRepository.findAll();
    }
}
