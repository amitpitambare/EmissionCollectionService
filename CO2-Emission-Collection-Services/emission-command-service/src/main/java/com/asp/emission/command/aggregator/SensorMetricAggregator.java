package com.asp.emission.command.aggregator;

import com.asp.emission.command.event.AddSensorMeasurementEvent;
import com.asp.emission.entity.Sensor;
import com.asp.emission.entity.SensorMetrics;
import com.asp.emission.enums.SensorStatus;

import com.asp.emission.repository.SensorRepository;
import com.asp.emission.repository.SensorMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableAsync
public class SensorMetricAggregator {


    private static final int CO2_CONCENTRATION_THRESHOLD =2000 ;
    @Autowired
    private SensorMetricsRepository sensorMetricsRepostory;

    @Autowired
    private SensorRepository sensorRepository;


    @Async
    @EventListener
    public void addSensorMeasurementEventListener(AddSensorMeasurementEvent event) {
        Optional<Sensor> optionalAnotherSensorMetricEntity = sensorRepository.findByUuid(event.getUuid());
        Sensor sensor = optionalAnotherSensorMetricEntity.orElse(new Sensor(event.getUuid()));


        SensorMetrics metrics = new SensorMetrics(event.getUuid(), event.getCo2(), event.getTime());
        List<SensorMetrics> sensorMetricsEntities = sensorMetricsRepostory.findTop2ByUuidOrderByTimeDesc(event.getUuid());

        if (event.getCo2() >= CO2_CONCENTRATION_THRESHOLD) {

            if (!sensorMetricsEntities.isEmpty() && sensorMetricsEntities.size() == 2 && sensorMetricsEntities.stream().allMatch(item -> item.getCo2() >= CO2_CONCENTRATION_THRESHOLD)) {

                sensor.setStatus(SensorStatus.ALERT);

            } else {
                sensor.setStatus(SensorStatus.WARN);
            }
        } else {
            if(!(sensorMetricsEntities.size() == 2  && sensorMetricsEntities.stream().anyMatch(item -> item.getCo2() >= CO2_CONCENTRATION_THRESHOLD) )){
                sensor.setStatus(SensorStatus.OK);
            }

        }
        sensorMetricsRepostory.save(metrics);
        sensorRepository.save(sensor);
    }
}
