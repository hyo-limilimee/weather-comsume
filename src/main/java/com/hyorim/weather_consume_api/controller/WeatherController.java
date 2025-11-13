package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.domain.WeatherResponse;
import com.hyorim.weather_consume_api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping("/predict")
    public WeatherResponse predict(@RequestBody WeatherRequest request) {
        return weatherService.predictWeatherConsume(request);
    }
}
