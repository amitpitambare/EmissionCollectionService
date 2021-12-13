package com.asp.emission.command.api;

import com.asp.emission.command.dto.AddSensorMeasurementCommandDto;
import com.asp.emission.command.service.SensorCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(value = "CO2 Emission Collection Service ", description = "the CO2 Emission Collection Command API")
@RestController
@RequestMapping("api/v1/sensors/")
public class SensorCollectionController {

    @Autowired
    private SensorCollectionService sensorCollectionService;

    @ApiOperation(value = "Add a Sensor CO2 measurement ", nickname = "collectSensorMeasurement")
    @ApiResponses({ @ApiResponse(code = 202, message = "Metrics Accepted") })
    @PostMapping("{uuid}/measurements")
    public ResponseEntity collectionSensorMeasurement(@PathVariable("uuid") String uuid, @RequestBody @Valid AddSensorMeasurementCommandDto addSensorMeasurement){
        this.sensorCollectionService.publishSensorMeasurementEvent(uuid,addSensorMeasurement);
        return ResponseEntity.accepted().build();
    }
}
