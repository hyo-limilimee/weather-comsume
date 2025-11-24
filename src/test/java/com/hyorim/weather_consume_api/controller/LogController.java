package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogController.class)
class LogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnalysisLogRepository repository;

    @Test
    void getLogs_정상조회() throws Exception {

        when(repository.findAll()).thenReturn(List.of(new AnalysisLog()));

        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk());
    }
}
