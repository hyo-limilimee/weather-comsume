package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.domain.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final LogService logService;

    private final WebClient webClient;

    @Value("${external.fastapi-url}")
    private String fastApiUrl;

    public WeatherResponse getPrediction(WeatherRequest request) {

        WeatherResponse response = webClient.post()
                .uri(fastApiUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();

        logService.saveLog(request, response.getPrediction());

        return response;
    }
}
