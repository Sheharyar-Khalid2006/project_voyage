package com.oop.voyage.project_voyage.services;

import com.oop.voyage.project_voyage.model.Driver;
import com.oop.voyage.project_voyage.model.Passenger;
import com.oop.voyage.project_voyage.model.Car;

import java.util.ArrayList;
import java.util.List;

// RideSession ties everything together for one active ride
public class RideSession {

    private final Driver            driver;
    private final Car               vehicle;
    private final List<Passenger>   passengers = new ArrayList<>();
    private final ProximityEngine   proximityEngine;
    private final AlarmService      alarmService;
    private boolean                 active;

    public RideSession(Driver driver, Car vehicle) {
        this.driver          = driver;
        this.vehicle         = vehicle;
        this.proximityEngine = new ProximityEngine();
        this.alarmService    = new AlarmService();
        this.active          = true;
    }

    public boolean addPassenger(Passenger p) {
        if (vehicle.boardPassenger()) {
            passengers.add(p);
            proximityEngine.subscribe(p); // p now gets location updates for the vehicle
            return true;
        }
        return false; // no seats
    }

    public void removePassenger(Passenger p) {
        passengers.remove(p);
        proximityEngine.unsubscribe(p);
        vehicle.alightPassenger();
        p.setBoarded(false);
    }

    // Call this from a timer every few seconds with updated GPS coordinates to fetch fresh location coordinates
    public void updateLocation(double lat, double lng, double speedKmh) {
        driver.setLocation(lat, lng);
        vehicle.setCurrentSpeed(speedKmh);
        proximityEngine.updateVehicleLocation(lat, lng);
    }

    // Getters methods
    public int    getAvailableSeats()
        { return vehicle.getAvailableSeats(); }
    public Driver getDriver()
        { return driver; }
    public Car    getVehicle()
        { return vehicle; }

    public double getETA(double destLat, double destLng) {
        return ProximityEngine.estimateETA(
                driver.getCurrentLat(), driver.getCurrentLng(),
                destLat, destLng, vehicle.getCurrentSpeed());
    }

    public String getFormattedETA(double destLat, double destLng) {
        double secs = getETA(destLat, destLng);
        if (secs == Double.MAX_VALUE)
            return "Calculation in progress, Please wait...";
        long mins = (long)(secs / 60);
        return mins + " mins away";
    }

    public void endRide() {
        active = false;
        alarmService.cancelAll();
        passengers.clear();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}