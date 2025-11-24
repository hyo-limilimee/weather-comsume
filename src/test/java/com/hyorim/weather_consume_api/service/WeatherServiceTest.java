package com.hyorim.weather_consume_api.service;

import com.hyorim.weather_consume_api.domain.WeatherRequest;
import com.hyorim.weather_consume_api.domain.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    private LogService logService;
    private WebClient webClient;
    private WeatherService weatherService;

    @BeforeEach
    void setup() throws Exception {
        logService = mock(LogService.class);

        WebClient.RequestBodyUriSpec bodyUriMock = mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestBodySpec bodySpecMock = mock(WebClient.RequestBodySpec.class);
        WebClient.RequestHeadersSpec reqHeaderMock = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);

        webClient = mock(WebClient.class);

        when(webClient.post()).thenReturn(bodyUriMock);
        when(bodyUriMock.uri(anyString())).thenReturn(bodySpecMock);
        when(bodySpecMock.bodyValue(any())).thenReturn(reqHeaderMock);
        when(reqHeaderMock.retrieve()).thenReturn(responseSpecMock);

        WeatherResponse mockResp = new WeatherResponse(10000.0, null);
        when(responseSpecMock.bodyToMono(WeatherResponse.class))
                .thenReturn(Mono.just(mockResp));

        weatherService = new WeatherService(logService, webClient);

        Field field = WeatherService.class.getDeclaredField("fastApiUrl");
        field.setAccessible(true);
        field.set(weatherService, "http://mock-url");
    }

    @Test
    void getPrediction_정상동작() {
        WeatherRequest req = new WeatherRequest();
        req.setAvgTemp(20.0);
        req.setRainfall(5.0);
        req.setHumidity(70.0);

        WeatherResponse resp = weatherService.getPrediction(req);

        assertThat(resp.getPrediction()).isEqualTo(10000.0);

        assertThat(resp.getMessage())
                .startsWith("예상 지하철 승객 수는 약")
                .contains("10,000")
                .endsWith("입니다.");

        verify(logService, times(1)).saveLog(req, 10000.0);
    }
}
