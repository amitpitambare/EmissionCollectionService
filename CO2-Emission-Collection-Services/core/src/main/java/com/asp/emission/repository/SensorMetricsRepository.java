package com.asp.emission.repository;

import com.asp.emission.entity.SensorMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorMetricsRepository extends MongoRepository<SensorMetrics, String> {
    List<SensorMetrics> findTop3ByTime();

    List<SensorMetrics> findTop2ByUuidOrderByTimeDesc(String uuid);

    Optional<SensorMetrics> findByUuid(String uuid);
}
