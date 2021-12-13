package com.asp.emission.command.service;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;
import com.asp.emission.command.event.AddSensorMeasurementEvent;
import com.asp.emission.command.event.EventService;
import com.asp.emission.command.serviceimpl.DefaultSensorCollectionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SensorCollectionServiceTest {
    @Mock
    private EventService eventService;

    @InjectMocks // auto inject DefaultSensorCollectionServiceImpl
    private SensorCollectionService sensorCollectionService = new DefaultSensorCollectionServiceImpl();


    @DisplayName("Test  SensorCollectionService + publishEvent")
    @Test
    public void testPublishSensorMeasurementEvent() {
        String testUUID="TESTUUID";
        AddSensorMeasurementEvent addSensorMeasurementEvent=new AddSensorMeasurementEvent(testUUID,1000,new Date());
        AddSensorMeasurementCommandDto inputDto = new AddSensorMeasurementCommandDto(1000, new Date());
        Mockito.doNothing().when(eventService).publishSensorMeasurementEvent(addSensorMeasurementEvent);
        sensorCollectionService.publishSensorMeasurementEvent(testUUID,inputDto);
        Mockito.verify(eventService,Mockito.times(1)).publishSensorMeasurementEvent(addSensorMeasurementEvent);

    }
}
