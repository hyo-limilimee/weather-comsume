package com.hyorim.weather_consume_api.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisLogTest {

    @Test
    void analysisLogBuilder_정상생성() {
        AnalysisLog log = AnalysisLog.builder()
                .avgTemp(20.5)
                .rainfall(3.2)
                .humidity(60.0)
                .prediction(10000.5)
                .createdAt("2025-11-24 00:00:00")
                .build();

        assertThat(log.getAvgTemp()).isEqualTo(20.5);
        assertThat(log.getRainfall()).isEqualTo(3.2);
        assertThat(log.getHumidity()).isEqualTo(60.0);
        assertThat(log.getPrediction()).isEqualTo(10000.5);
        assertThat(log.getCreatedAt()).isEqualTo("2025-11-24 00:00:00");
    }
}
