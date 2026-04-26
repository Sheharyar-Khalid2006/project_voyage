package com.oop.voyage.project_voyage.services;

import com.oop.voyage.project_voyage.interfaces.LocationObserver;
import java.util.ArrayList;
import java.util.List;

public class ProximityEngine {

    private final List<LocationObserver> observers = new ArrayList<>();
    private double vehicleLat;
    private double vehicleLng;

    // Haversine formula — real distance between 2 GPS coordinates in METRES
    public static double haversineDistance(double lat1, double lng1,
                                           double lat2, double lng2) {
        final double R = 6371000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        // Implementation of proposed proximity detection radius by equation
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    public void subscribe(LocationObserver obs)   { observers.add(obs); }
    public void unsubscribe(LocationObserver obs) { observers.remove(obs); }

    // Location Updater for vehicle (can be run to refresh and check vehicle location)
    public void updateVehicleLocation(double lat, double lng) {
        this.vehicleLat = lat;
        this.vehicleLng = lng;
        notifyObservers(lat, lng);
    }

    private void notifyObservers(double lat, double lng) {
        for (LocationObserver o : observers) {
            o.onLocationUpdate(lat, lng);
        }
    }

    // ETA estimater: distance / speed
    public static double estimateETA(double vehicleLat, double vehicleLng,
                                     double destLat,    double destLng,
                                     double speedKmh) {
        if (speedKmh <= 0)
            return Double.MAX_VALUE; // Slower speed then minimum
        double distMetres = haversineDistance(vehicleLat, vehicleLng, destLat, destLng);
        double speedMs    = speedKmh / 3.6;
        return distMetres / speedMs;
    }
}