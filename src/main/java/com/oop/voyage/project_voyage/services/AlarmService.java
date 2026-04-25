package com.oop.voyage.project_voyage.services;

import com.oop.voyage.project_voyage.interfaces.Notifiable;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmService {

    private Timer timer;

    // Time-based alarm at a specific LocalTime
    public void setTimedAlarm(Notifiable target, int hour, int minute, String message) {
        if (timer != null) timer.cancel();
        timer = new Timer(true); // daemon thread

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();
                if (now.getHour() == hour && now.getMinute() == minute) {
                    target.receiveNotification("Timed Alarm: " + message);
                    cancel(); // run alarm once
                }
            }
        }, 0, 30_000); // check at every 30 seconds delayed time
    }

    // Proximity alarm runs when distance drops below threshold metres
    public static boolean checkProximityAlarm(double vehicleLat, double vehicleLng,
                                              double destLat,    double destLng,
                                              double thresholdMetres,
                                              Notifiable target) {
        double dist = ProximityEngine.haversineDistance(
                vehicleLat, vehicleLng, destLat, destLng);
        if (dist <= thresholdMetres) {
            target.receiveNotification(
                    "Wake Up! You are " + (int) dist + "meters from your destination.");
            return true;
        }
        return false;
    }

    public void cancelAll() {
        if (timer != null) timer.cancel();
    }
}