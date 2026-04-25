package com.oop.voyage.project_voyage.model;

import com.oop.voyage.project_voyage.interfaces.Notifiable;
import com.oop.voyage.project_voyage.interfaces.LocationObserver;
import com.oop.voyage.project_voyage.services.ProximityEngine;

public class Passenger extends User implements Notifiable, LocationObserver {

    private double  pickupLat;
    private double  pickupLng;
    private double  destLat;
    private double  destLng;
    private boolean isBoarded;
    private boolean alarmEnabled;
    private int     alarmHr;
    private int     alarmMin;

    public Passenger(String cnic, String phone, String email) {
        super(cnic, phone, email);
        this.alarmEnabled = true;
        this.isBoarded    = false;
    }

    @Override
    public String getRole()
        { return "PASSENGER"; }

    @Override
    public void onLocationUpdate(double vLat, double vLng) {
        if (!isBoarded || !alarmEnabled) return;
        double dist = ProximityEngine.haversineDistance(vLat, vLng, destLat, destLng);
        if (dist <= 500) {
            receiveNotification("Proximity Alert: You are " + (int) dist + "m from your destination!");
        }
    }

    @Override
    public void receiveNotification(String msg) {
        System.out.println("[PASSENGER ALERT] " + getCnic() + ": " + msg);
    }

    public double  getDestinationLat()
        { return destLat; }
    public double  getDestinationLng()
        { return destLng; }
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
        { this.destLat = lat; this.destLng = lng; }
    public void    setTimedAlarm(int hr, int min)
        { this.alarmHr = hr; this.alarmMin = min; }
    public int     getTimedAlarmHour()
        { return alarmHr; }
    public int     getTimedAlarmMinute()
        { return alarmMin; }

    public String getPublicInfo() {
        return "Passenger | Pickup: (" + String.format("%.4f", pickupLat) +
                ", " + String.format("%.4f", pickupLng) + ")";
    }
}