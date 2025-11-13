package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.domain.WeatherResponse;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final AnalysisLogRepository logRepository;
    private final Random random = new Random();

    public WeatherResponse predictWeatherConsume(WeatherRequest request) {
        double prediction = (request.getAvgTemp() * 1000)
                - (request.getHumidity() * 30)
                + (request.getRainfall() * 50)
                + random.nextDouble() * 500;

        AnalysisLog log = AnalysisLog.builder()
                .requestTime(LocalDateTime.now())
                .inputData(String.format("avgTemp=%.1f, rainfall=%.1f, humidity=%.1f",
                        request.getAvgTemp(), request.getRainfall(), request.getHumidity()))
                .predictionResult(String.format("%.2f", prediction))
                .build();

        logRepository.save(log);

        return new WeatherResponse(prediction);
    }
}
