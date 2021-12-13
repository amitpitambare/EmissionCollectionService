package com.asp.emission.command.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSensorMeasurementCommandDto {

    @NotNull
    private Integer co2;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date time;
}
