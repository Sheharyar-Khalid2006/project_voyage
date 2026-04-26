package com.oop.voyage.project_voyage.services;

import com.oop.voyage.project_voyage.interfaces.Notifiable;
import javafx.application.Platform;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmService {

    private Timer timer;

    public void setTimedAlarm(Notifiable target, int hour, int minute, String msg) {
        if (timer != null) timer.cancel();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();
                if (now.getHour() == hour && now.getMinute() == minute) {
                    Platform.runLater(() ->
                            target.receiveNotification("Timed Alarm: " + msg));
                    cancelAll();
                }
            }
        }, 0, 1000);
    }

    public static boolean checkProximityAlarm(double vLat, double vLng,
                                              double dLat, double dLng,
                                              double threshMetres,
                                              Notifiable target) {
        double dist = ProximityEngine.haversineDistance(vLat, vLng, dLat, dLng);
        if (dist <= threshMetres) {
            Platform.runLater(() ->
                    target.receiveNotification("Wake Up! You are " + (int) dist + "m from your destination."));
            return true;
        }
        return false;
    }

    public void cancelAll() {
        if (timer != null) timer.cancel();
    }
}