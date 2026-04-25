package com.oop.voyage.project_voyage.interfaces;
// To update location when receive information
public interface LocationObserver {
    void onLocationUpdate(double vehicleLat, double vehicleLng);
}