package com.asp.emission.query.service;

import com.asp.emission.query.dto.SensorAlertsDto;
import com.asp.emission.query.dto.SensorMetricsDto;
import com.asp.emission.query.dto.SensorStatusDto;

import java.text.ParseException;

public interface SensorQueryService {
    SensorStatusDto fetchSensorStatus(String uuid);

    SensorMetricsDto fetchLast30DaysAverageMetrics(String uuid) throws ParseException;

    SensorAlertsDto fetchAlertsforSensor(String uuid);
}
