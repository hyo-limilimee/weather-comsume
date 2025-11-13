package com.hyorim.weather_consume_api.repository;

import com.hyorim.weather_consume_api.domain.AnalysisLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisLogRepository extends JpaRepository<AnalysisLog, Long> {
}
