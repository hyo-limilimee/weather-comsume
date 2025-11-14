package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.domain.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DecimalFormat;

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

        double rawPrediction = response.getPrediction();

        DecimalFormat df = new DecimalFormat("#,###.##");
        String formatted = df.format(rawPrediction);

        logService.saveLog(request, rawPrediction);

        String msg = "예상 지하철 승객 수는 약 " + formatted + " 입니다.";

        response.setMessage(msg);

        return response;
    }
}
