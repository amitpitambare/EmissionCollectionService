package com.asp.emission.entity;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String UUID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int measurement;
}
