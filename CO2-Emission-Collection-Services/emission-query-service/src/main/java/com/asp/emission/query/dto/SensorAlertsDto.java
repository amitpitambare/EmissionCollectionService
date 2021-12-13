package com.asp.emission.query.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SensorAlertsDto {

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date endTime;

    private Integer measurement1;
    private Integer measurement2;
    private Integer measurement3;
}
