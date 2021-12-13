package com.asp.emission.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensor_metrics")
public class SensorMetrics {
    private String uuid;
    private int co2;
    private Date time;
}
