package com.hyorim.weather_consume_api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double avgTemp;
    private double rainfall;
    private double humidity;

    private double prediction;

    private String createdAt;
}
