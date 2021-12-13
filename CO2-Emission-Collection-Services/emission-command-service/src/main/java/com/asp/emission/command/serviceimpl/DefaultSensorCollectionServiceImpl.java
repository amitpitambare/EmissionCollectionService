package com.asp.emission.command.serviceimpl;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;
import com.asp.emission.command.event.AddSensorMeasurementEvent;
import com.asp.emission.command.event.EventService;
import com.asp.emission.command.service.SensorCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DefaultSensorCollectionServiceImpl implements SensorCollectionService {


    @Autowired
    @Qualifier("springEventService")
    private EventService eventService;
    @Override
    public void publishSensorMeasurementEvent(String uuid, AddSensorMeasurementCommandDto addSensorMeasurementCommandDto) {
        this.eventService.publishSensorMeasurementEvent(new AddSensorMeasurementEvent(uuid,addSensorMeasurementCommandDto.getCo2(),addSensorMeasurementCommandDto.getTime()));
    }


}
