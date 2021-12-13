package com.asp.emission.entity;

import com.asp.emission.enums.SensorStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensor")
public class Sensor {

        @Id
        private String uuid;

        private SensorStatus status;

        public Sensor(String uuid) {
                this.uuid = uuid;
        }
}
