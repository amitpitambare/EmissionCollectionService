package com.asp.emission.command.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service("springEventService")
public class DefaultSpringEventService implements EventService {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Override
    public void publishSensorMeasurementEvent(AddSensorMeasurementEvent addSensorMeasurementEvent) {
        publisher.publishEvent(addSensorMeasurementEvent);
    }
}
