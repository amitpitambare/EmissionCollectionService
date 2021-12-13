package com.asp.emission.repository;

import com.asp.emission.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String> {


    Optional<Sensor> findByUuid(String uuid);
}
