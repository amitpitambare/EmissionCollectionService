package com.asp.emission.query.api;

import com.asp.emission.query.dto.SensorAlertsDto;
import com.asp.emission.query.dto.SensorMetricsDto;
import com.asp.emission.query.dto.SensorStatusDto;
import com.asp.emission.query.service.SensorQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
@Api(value = "CO2 Emission Collection Service ", description = "the CO2 Emission Collection QUERY APIs")
@RestController
@RequestMapping("api/v1/sensors/")
public class SensorQueryController {

    @Autowired
    private SensorQueryService sensorQueryService;

    @ApiOperation(value = "Fetch Sensor Status", nickname = "fetchSensorStatus")
    @ApiResponses({ @ApiResponse(code = 200, message = "Sensor Status Fetched", response = SensorStatusDto.class) })
    @GetMapping("{uuid}")
    public ResponseEntity<SensorStatusDto> fetchSensorStatus(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(this.sensorQueryService.fetchSensorStatus(uuid));

    }

    @ApiOperation(value = "Fetch Sensor Metrics for last 30 days", nickname = "fetchLast30DaysAverageMetrics")
    @ApiResponses({ @ApiResponse(code = 200, message = "Sensor Metrics Fetched", response = SensorMetricsDto.class) })
    @GetMapping("{uuid}/metrics")
    public ResponseEntity<SensorMetricsDto> fetchLast30DaysAverageMetrics(@PathVariable("uuid") String uuid) throws ParseException {
        return ResponseEntity.ok(this.sensorQueryService.fetchLast30DaysAverageMetrics(uuid));

    }


    @GetMapping("{uuid}/alerts")
    public ResponseEntity<SensorAlertsDto> fetchAlertsforSensor(@PathVariable("uuid") String uuid) throws ParseException {
        return ResponseEntity.ok(this.sensorQueryService.fetchAlertsforSensor(uuid));

    }

}
