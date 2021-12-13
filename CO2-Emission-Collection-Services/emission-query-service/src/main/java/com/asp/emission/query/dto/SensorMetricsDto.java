package com.asp.emission.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorMetricsDto {

    private int maxLast30Days;
    private int avgLast30Days;
}
