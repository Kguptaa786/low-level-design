package com.kgdev.model;

import lombok.Data;

import javax.xml.stream.Location;
import java.time.LocalDateTime;

@Data
public class Ride {
    private String id;
    private Location startLocation;
    private Location endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Payment payment;
    private Driver driver;
    private Rider rider;
}
