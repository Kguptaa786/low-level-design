package com.kgdev.model;

import com.kgdev.enums.VehicleStatus;
import lombok.Data;


@Data
public abstract class Vehicle {
    private String vehicleNumber;
    private String registrationNumber;
    private VehicleStatus status;
}
