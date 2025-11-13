package com.hyorim.weather_consume_api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherResponse {
    private Double prediction;
}
