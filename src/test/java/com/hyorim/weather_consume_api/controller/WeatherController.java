package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.domain.WeatherResponse;
import com.hyorim.weather_consume_api.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherService weatherService;

    @Test
    void predict_정상동작() throws Exception {

        when(weatherService.getPrediction(any()))
                .thenReturn(new WeatherResponse(10000.0, "ok"));

        mockMvc.perform(post("/api/predict")
                        .contentType("application/json")
                        .content("{\"avgTemp\":20, \"rainfall\":5, \"humidity\":60}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prediction").value(10000.0));
    }
}
