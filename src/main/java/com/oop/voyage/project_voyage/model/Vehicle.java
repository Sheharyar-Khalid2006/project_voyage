package com.oop.voyage.project_voyage.model;

public abstract class Vehicle {
    private String vehicleType;
    private String numberPlate;
    private int    totalSeats;
    private int    occupiedSeats;
    private double currentSpeed; // speed in km/h

    public Vehicle(String vehicleType, String numberPlate, int totalSeats) {
        this.vehicleType   = vehicleType;
        this.numberPlate   = numberPlate;
        this.totalSeats    = totalSeats;
        this.occupiedSeats = 0;
    }

    public int    getAvailableSeats()
        { return totalSeats - occupiedSeats; }
    public int    getTotalSeats()
        { return totalSeats; }
    public int    getOccupiedSeats()
        { return occupiedSeats; }
    public String getVehicleType()
        { return vehicleType; }
    public String getNumberPlate()
        { return numberPlate; }
    public double getCurrentSpeed()
        { return currentSpeed; }
    public void   setCurrentSpeed(double s)
        { this.currentSpeed = Math.max(0, s); }

    public boolean boardPassenger() {
        if (occupiedSeats < totalSeats) {
            occupiedSeats++;
            return true;   //seats available
        }
        return false; // seats are full
    }

    public void alightPassenger() {
        if (occupiedSeats > 0) occupiedSeats--;
    }

    public abstract int getSeatsFromType(String type);
}