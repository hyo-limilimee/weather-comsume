package com.hyorim.weather_consume_api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherRequest {
    private Double avgTemp;
    private Double rainfall;
    private Double humidity;
}
