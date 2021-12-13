package com.asp.emission.query.serviceimpl;

import com.asp.emission.entity.Sensor;
import com.asp.emission.exception.CustomApplicationException;
import com.asp.emission.query.dto.SensorAlertsDto;
import com.asp.emission.query.dto.SensorMetricsDto;
import com.asp.emission.query.dto.SensorStatusDto;
import com.asp.emission.query.service.SensorQueryService;
import com.asp.emission.repository.SensorMetricsRepository;
import com.asp.emission.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DefaultSensorQueryServiceImpl implements SensorQueryService {


    @Autowired
    private SensorMetricsRepository sensorMetricsRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public SensorStatusDto fetchSensorStatus(String uuid) {
        Optional<Sensor> optionalSensorEntity = sensorRepository.findByUuid(uuid);
        Sensor sensorMetricsEntity = optionalSensorEntity.orElseThrow(() -> new CustomApplicationException("NOT FOUND"));
        return new SensorStatusDto(sensorMetricsEntity.getStatus().name());
    }

    @Override
    public SensorMetricsDto fetchLast30DaysAverageMetrics(String uuid) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        Date startDate = df.parse(df.format(new Date()));


        Aggregation agg = Aggregation.newAggregation(Aggregation.match(new Criteria("uuid").is(uuid)),
                Aggregation.match(Criteria.where("time").lte(startDate)),
                Aggregation.group(uuid).avg("co2").as("avgLast30Days").max("co2")
                        .as("maxLast30Days"),
                Aggregation.limit(1));
        AggregationResults<SensorMetricsDto> sensorMetricsDto = mongoTemplate.aggregate(agg, "sensor_metrics",
                SensorMetricsDto.class);
        List<Map<String, Object>> documents = (List<Map<String, Object>>) sensorMetricsDto.getRawResults()
                .get("results");
        if (documents.size() > 0 ) {
            return new SensorMetricsDto();
        } else {
            return sensorMetricsDto.getUniqueMappedResult();
        }
    }

    @Override
    public SensorAlertsDto fetchAlertsforSensor(String uuid) {
       throw new UnsupportedOperationException();
    }
}
