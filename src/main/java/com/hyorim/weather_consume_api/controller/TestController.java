package com.hyorim.weather_consume_api.controller;

import com.hyorim.weather_consume_api.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/api/test")
    public String test() {
        return testService.getStatusMessage();
    }
}
