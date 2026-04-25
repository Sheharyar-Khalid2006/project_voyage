package com.oop.voyage.project_voyage.model;

import com.oop.voyage.project_voyage.interfaces.Notifiable;
import com.oop.voyage.project_voyage.interfaces.LocationObserver;

public class Passenger extends User implements Notifiable, LocationObserver {
    private double pickupLat;
    private double pickupLng;
    private double destinationLat;
    private double destinationLng;
    private boolean isBoarded;
    private boolean alarmEnabled;
    private int     timedAlarmHour;   // 24h time format
    private int     timedAlarmMinute;

    public Passenger(String cnic, String phone, String email) {
        super(cnic, phone, email);
        this.alarmEnabled = true;
        this.isBoarded    = false;
    }

    @Override public String getRole() { return "PASSENGER"; }

    // Called by ProximityEngine when vehicle location updates
    @Override
    public void onLocationUpdate(double vehicleLat, double vehicleLng) {
        if (!isBoarded || !alarmEnabled) return;
        double dist = ProximityEngine.haversineDistance(
                vehicleLat, vehicleLng, destinationLat, destinationLng);
        if (dist <= 500) {  // within 500 metres
            receiveNotification("Proximity Alert: You are " +(int) dist +
                    "m from your destination!");
        }
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("[PASSENGER ALERT] " + getCnic() + ": " + message);
    }

    // Getters and Setters method
    public double  getDestinationLat()
        { return destinationLat; }
    public double  getDestinationLng()
        { return destinationLng; }
    public boolean isBoarded()
        { return isBoarded; }
    public boolean isAlarmEnabled()
        { return alarmEnabled; }
    public void    setBoarded(boolean b)
        { this.isBoarded = b; }
    public void    setAlarmEnabled(boolean e)
        { this.alarmEnabled = e; }
    public void    setPickup(double lat, double lng)
        { this.pickupLat = lat; this.pickupLng = lng; }
    public void    setDestination(double lat, double lng)
        { this.destinationLat = lat; this.destinationLng = lng; }
    public void    setTimedAlarm(int hour, int minute) {
        this.timedAlarmHour   = hour;
        this.timedAlarmMinute = minute;
    }
    public int getTimedAlarmHour()
        { return timedAlarmHour; }
    public int getTimedAlarmMinute()
        { return timedAlarmMinute; }

    // Hiding sensitive info of passenger from driver (this is what the driver sees)
    public String getPublicInfo() {
        return "Passenger | Pickup: (" + String.format("%.4f", pickupLat) +
                ", " + String.format("%.4f", pickupLng) + ")";
    }
}