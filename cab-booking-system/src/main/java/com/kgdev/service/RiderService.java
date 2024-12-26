package com.kgdev.service;

import com.kgdev.enums.PayMode;
import com.kgdev.model.Address;

public interface RiderService {
    void book(String riderId, Address source, Address destination);
    void pay(String rideId, double amount, PayMode payMode);
    void getHistory(String riderId);
}
