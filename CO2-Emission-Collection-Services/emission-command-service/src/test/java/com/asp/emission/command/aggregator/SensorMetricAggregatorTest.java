package com.asp.emission.command.aggregator;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;
import com.asp.emission.command.event.AddSensorMeasurementEvent;
import com.asp.emission.command.event.DefaultSpringEventService;
import com.asp.emission.command.event.EventService;
import com.asp.emission.command.service.SensorCollectionService;
import com.asp.emission.command.serviceimpl.DefaultSensorCollectionServiceImpl;
import com.asp.emission.entity.Sensor;
import com.asp.emission.entity.SensorMetrics;
import com.asp.emission.enums.SensorStatus;
import com.asp.emission.repository.SensorMetricsRepository;
import com.asp.emission.repository.SensorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class SensorMetricAggregatorTest {

    @Mock
    private SensorMetricsRepository sensorMetricsRepostory;

    @Mock
    private SensorRepository sensorRepostory;

    @Spy
    private EventService eventService=new DefaultSpringEventService();



    @InjectMocks // auto inject DefaultSensorCollectionServiceImpl
    private SensorMetricAggregator sensorMetricAggregator;



    @Test
    public void testAddSensorMeasurementEventListener_firstMetric() {
        String testUUID="TESTUUID";
        Date inputDate=new Date();
        AddSensorMeasurementEvent addSensorMeasurementEvent=new AddSensorMeasurementEvent(testUUID,1000,inputDate);

        Sensor inputSensor=new Sensor(testUUID);
        Sensor expectedSensor=inputSensor;
        expectedSensor.setStatus(SensorStatus.OK);


        SensorMetrics metrics = new SensorMetrics(testUUID, 1000, inputDate);

        Mockito.when(sensorRepostory.findByUuid(addSensorMeasurementEvent.getUuid())).thenReturn(java.util.Optional.of(new Sensor(testUUID)));
        Mockito.when(sensorMetricsRepostory.findTop2ByUuidOrderByTimeDesc(testUUID)).thenReturn(new ArrayList<SensorMetrics>());
        Mockito.when(sensorRepostory.save(inputSensor)).thenReturn(inputSensor);
        Mockito.when(sensorMetricsRepostory.save(metrics)).thenReturn(metrics);
        sensorMetricAggregator.addSensorMeasurementEventListener(addSensorMeasurementEvent);
        Assertions.assertEquals(expectedSensor.getStatus(),inputSensor.getStatus());


    }



    @Test
    public void testAddSensorMeasurementEventListener_Co2MetricGreaterThan2000() {
        String testUUID="TESTUUID";
        Date inputDate=new Date();
        AddSensorMeasurementEvent addSensorMeasurementEvent=new AddSensorMeasurementEvent(testUUID,2100,inputDate);

        Sensor inputSensor=new Sensor(testUUID);
        Sensor expectedSensor=inputSensor;
        expectedSensor.setStatus(SensorStatus.WARN);


        SensorMetrics metrics = new SensorMetrics(testUUID, 2100, inputDate);

        Mockito.when(sensorRepostory.findByUuid(addSensorMeasurementEvent.getUuid())).thenReturn(java.util.Optional.of(new Sensor(testUUID)));
        Mockito.when(sensorMetricsRepostory.findTop2ByUuidOrderByTimeDesc(testUUID)).thenReturn(new ArrayList<SensorMetrics>());
        Mockito.when(sensorRepostory.save(inputSensor)).thenReturn(inputSensor);
        Mockito.when(sensorMetricsRepostory.save(metrics)).thenReturn(metrics);
        sensorMetricAggregator.addSensorMeasurementEventListener(addSensorMeasurementEvent);
        Assertions.assertEquals(expectedSensor.getStatus(),inputSensor.getStatus());


    }
}
