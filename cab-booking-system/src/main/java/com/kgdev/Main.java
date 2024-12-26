package com.kgdev;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


/*


Flow of cab booking system
1. Rider open app
2. Enter destination address
3. Confirm button click which will show fare price
3. Hit proceed button and request will send to the driver
4. All driver (near to rider) will receive rider request
5. One of the driver will accept the request
6. Rider will notify that this driver is accepted the ride and will be reaching in T time
7. Driver reach to the rider location, take otp and start ride
8. Driver reach to the destination location, take payment and end the trip

NOTE: There is a service which take address and return lat/long;

Driver
void updateStatus(String driverId);
void acceptRide(String riderId, Address source, Address destination);
void startRide(int otp, String rideId);
void endRide(String rideId, boolean cashPayment);


Rider
void book(String riderId, Address source, Address destination);
void pay(String rideId, double amount, PayMode payMode);

Admin
void registerRider(String firstName, String lastName, String mobileNumber, String street, String city, int pinCode)
void registerDriver(String firstName, String lastName, String mobileNumber, String street, String city, int pinCode, String vehicleNumber, String registrationNumber, VehicleType vehicleType);

*/