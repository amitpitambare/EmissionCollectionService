package com.asp.emission.command.event;

import org.springframework.stereotype.Service;

@Service("kafkaEventService")
public class KafkaEventService implements EventService {
    @Override
    public void publishSensorMeasurementEvent(AddSensorMeasurementEvent addSensorMeasurementEvent) {
        throw new UnsupportedOperationException();
    }
}
