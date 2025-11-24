package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.repository.AnalysisLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class LogServiceTest {

    private final AnalysisLogRepository repository = mock(AnalysisLogRepository.class);
    private final LogService logService = new LogService(repository);

    @Test
    void saveLog_정상저장() {

        WeatherRequest req = new WeatherRequest();
        req.setAvgTemp(10.0);
        req.setRainfall(5.0);
        req.setHumidity(80.0);

        logService.saveLog(req, 12345.6);

        ArgumentCaptor<AnalysisLog> captor = ArgumentCaptor.forClass(AnalysisLog.class);
        verify(repository, times(1)).save(captor.capture());

        AnalysisLog saved = captor.getValue();

        assertThat(saved.getAvgTemp()).isEqualTo(10.0);
        assertThat(saved.getRainfall()).isEqualTo(5.0);
        assertThat(saved.getHumidity()).isEqualTo(80.0);
        assertThat(saved.getPrediction()).isEqualTo(12345.6);
        assertThat(saved.getCreatedAt()).isNotNull();
    }
}
