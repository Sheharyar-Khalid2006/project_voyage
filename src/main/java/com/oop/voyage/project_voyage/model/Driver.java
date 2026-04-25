package com.oop.voyage.project_voyage.model;

import com.oop.voyage.project_voyage.interfaces.Notifiable;

public class Driver extends User implements Notifiable {
    private String vehicleType;
    private String numberPlate;
    private boolean available;
    private double currentLat;  //Vehicle Cordinates
    private double currentLng;

    public Driver(String cnic, String phone, String email,
                  String vehicleType, String numberPlate) {
        super(cnic, phone, email);
        this.vehicleType = vehicleType;
        this.numberPlate = numberPlate;
        this.available   = true;
    }

    @Override public String getRole()
        { return "DRIVER"; }

    // Getters & Setters
    public String  getVehicleType()
        { return vehicleType; }
    public String  getNumberPlate()
        { return numberPlate; }
    public boolean isAvailable()
        { return available; }
    public double  getCurrentLat()
        { return currentLat; }
    public double  getCurrentLng()
        { return currentLng; }
    public void    setAvailable(boolean a)
        { this.available = a; }
    public void    setLocation(double lat, double lng) {
        this.currentLat = lat;
        this.currentLng = lng;
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("[DRIVER ALERT] " + getNumberPlate() + ": " + message);
    }

    // Driver calls this manually to wake a sleeping passenger
    public void wakePassenger(Passenger p) {
        p.receiveNotification("Driver Alert: Your stop is coming! Please prepare to exit.");
    }
}