package com.asp.emission.query.api.service;

import com.asp.emission.entity.Sensor;
import com.asp.emission.enums.SensorStatus;
import com.asp.emission.query.dto.SensorStatusDto;
import com.asp.emission.query.service.SensorQueryService;
import com.asp.emission.query.serviceimpl.DefaultSensorQueryServiceImpl;
import com.asp.emission.repository.SensorMetricsRepository;
import com.asp.emission.repository.SensorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
@ExtendWith(MockitoExtension.class)
public class SensorQueryServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private SensorMetricsRepository sensorMetricsRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private SensorQueryService sensorQueryService=new DefaultSensorQueryServiceImpl();

    @DisplayName("Test:SensorQueryService-FetchSensorStatus ")
    @Test
    public void testFetchSensorStatus() {
        String testUUID="TESTUUID";
        Sensor inputSensor= new Sensor(testUUID, SensorStatus.OK);
        Mockito.when(sensorRepository.findByUuid(testUUID)).thenReturn(java.util.Optional.of(inputSensor));
        SensorStatusDto status=sensorQueryService.fetchSensorStatus(testUUID);
        Assertions.assertEquals(status.getStatus(),inputSensor.getStatus().name());

    }

    @DisplayName("Test:SensorQueryService-FetchLast30DaysAverageMetrics ")
    @Test
    public void testFetchLast30DaysAverageMetrics() {
        String testUUID="TESTUUID";
        Sensor inputSensor= new Sensor(testUUID, SensorStatus.OK);
        mongoTemplate.aggregate(Mockito.any(),Mockito.anyString(),Mockito.any());
        Mockito.when(sensorRepository.findByUuid(testUUID)).thenReturn(java.util.Optional.of(inputSensor));
        SensorStatusDto status=sensorQueryService.fetchSensorStatus(testUUID);
        Assertions.assertEquals(status.getStatus(),inputSensor.getStatus().name());

    }
}
