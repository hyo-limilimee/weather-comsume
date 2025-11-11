package com.hyorim.weather_consume_api.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String getStatusMessage() {
        return "✅ Weather Consume API 서버가 정상 작동 중입니다!";
    }
}
