package com.kgdev.service;

import com.kgdev.model.Address;

public interface DriverService {
    void updateStatus(String driverId);
    void updateLocation(Address location);
    void acceptRide(String riderId, Address source, Address destination);
    void startRide(int otp, String rideId);
    void endRide(String rideId, boolean cashPayment);
}
