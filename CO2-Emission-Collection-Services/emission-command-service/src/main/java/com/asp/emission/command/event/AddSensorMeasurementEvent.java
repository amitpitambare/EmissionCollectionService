package com.asp.emission.command.event;

import lombok.Data;

import java.util.Date;

@Data
public class AddSensorMeasurementEvent {
    private String uuid;
    private int co2;
    private Date time;

    public AddSensorMeasurementEvent(String uuid,int co2, Date localDateTime) {
        this.uuid=uuid;
        this.co2 = co2;
        this.time = localDateTime;
    }
}
