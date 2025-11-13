package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LogService {

    private final AnalysisLogRepository logRepository;

    public void saveLog(WeatherRequest req, double prediction) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        AnalysisLog log = AnalysisLog.builder()
                .avgTemp(req.getAvgTemp())
                .rainfall(req.getRainfall())
                .humidity(req.getHumidity())
                .prediction(prediction)
                .createdAt(timestamp)
                .build();

        logRepository.save(log);
    }
}
