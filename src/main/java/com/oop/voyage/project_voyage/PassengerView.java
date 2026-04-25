package com.oop.voyage.project_voyage;

import com.oop.voyage.project_voyage.model.Passenger;
import com.oop.voyage.project_voyage.services.AlarmService;
import com.oop.voyage.project_voyage.services.FareCalculator;
import com.oop.voyage.project_voyage.services.ProximityEngine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerView implements Initializable {

    @FXML private Label     statusLabel;
    @FXML private Label     fareLabel;
    @FXML private Label     etaLabel;
    @FXML private Label     alarmStatusLabel;
    @FXML private Label     seatsLabel;
    @FXML private TextField destLatField;
    @FXML private TextField destLngField;
    @FXML private TextField alarmHrField;
    @FXML private TextField alarmMinField;

    private Passenger      pax;
    private AlarmService   alarmSvc;
    private boolean        proximAlarmFired = false;

    // simulated vehicle position
    private double vehLat = 33.1450;
    private double vehLng = 73.7510;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alarmSvc = new AlarmService();
    }

    public void initPassenger(Passenger p) {
        this.pax = p;
        statusLabel.setText("Welcome! Set your destination below.");
    }

    @FXML
    private void onConfirmDestination() {
        try {
            double dLat = Double.parseDouble(destLatField.getText().trim());
            double dLng = Double.parseDouble(destLngField.getText().trim());
            pax.setDestination(dLat, dLng);
            pax.setBoarded(true);

            double fare = FareCalculator.calculateFare(vehLat, vehLng, dLat, dLng,
                    "Sedan / Toyota (5 seater)");
            fareLabel.setText("Estimated fare: " + FareCalculator.formatFare(fare));

            double etaSecs = ProximityEngine.estimateETA(vehLat, vehLng, dLat, dLng, 40);
            long   etaMins = (long)(etaSecs / 60);
            etaLabel.setText("ETA: ~" + etaMins + " min");

            startProximityCheck(dLat, dLng);
            statusLabel.setText("Ride booked! Proximity alarm is active.");
        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter valid coordinates.");
        }
    }

    @FXML
    private void onSetTimedAlarm() {
        try {
            int hr  = Integer.parseInt(alarmHrField.getText().trim());
            int min = Integer.parseInt(alarmMinField.getText().trim());
            pax.setTimedAlarm(hr, min);
            alarmSvc.setTimedAlarm(pax, hr, min, "Time to get off soon!");
            alarmStatusLabel.setText("Timed alarm set for " + hr + ":" +
                    String.format("%02d", min));
        } catch (NumberFormatException ex) {
            alarmStatusLabel.setText("Enter valid hour and minute.");
        }
    }

    @FXML
    private void onToggleProximityAlarm() {
        pax.setAlarmEnabled(!pax.isAlarmEnabled());
        alarmStatusLabel.setText("Proximity alarm: " +
                (pax.isAlarmEnabled() ? "ON" : "OFF"));
    }

    private void startProximityCheck(double dLat, double dLng) {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            vehLat += 0.0008;
            vehLng += 0.0005;
            pax.onLocationUpdate(vehLat, vehLng);

            double dist = ProximityEngine.haversineDistance(vehLat, vehLng, dLat, dLng);
            etaLabel.setText("Distance to stop: " + (int) dist + "m");

            if (!proximAlarmFired && dist <= 500) {
                proximAlarmFired = true;
                statusLabel.setText("🔔 WAKE UP! You are " + (int)dist + "m from your stop!");
                statusLabel.setStyle("-fx-text-fill: #ff4444; -fx-font-weight: bold;");
            }
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
}