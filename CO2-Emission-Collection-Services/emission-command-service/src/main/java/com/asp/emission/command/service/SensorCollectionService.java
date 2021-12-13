package com.asp.emission.command.service;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;

public interface SensorCollectionService {

    void publishSensorMeasurementEvent(String uuid, AddSensorMeasurementCommandDto addSensorMeasurementCommandDto);

}
