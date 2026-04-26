package com.oop.voyage.project_voyage;

import com.oop.voyage.project_voyage.model.Passenger;
import com.oop.voyage.project_voyage.services.AlarmService;
import com.oop.voyage.project_voyage.services.FareCalculator;
import com.oop.voyage.project_voyage.services.ProximityEngine;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

    private Passenger    pax;
    private AlarmService alarmSvc;
    private boolean      proximAlarmFired = false;

    private double vehLat = 33.1450;
    private double vehLng = 73.7510;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alarmSvc = new AlarmService();
    }

    public void initPassenger(Passenger p) {
        this.pax = p;
        statusLabel.setText("Welcome! Set your destination below.");
        seatsLabel.setText("--");

        // Hook passenger notifications into the UI label
        p.setNotificationCallback(msg ->
                Platform.runLater(() -> showAlarmBanner(msg)));
    }

    private void showAlarmBanner(String msg) {
        statusLabel.setText(msg);
        statusLabel.setStyle("-fx-text-fill: #ff4444; -fx-font-weight: bold;");
        alarmStatusLabel.setText(msg);

        try {
            URL audioUrl = getClass().getResource("alarm.mp3");
            if (audioUrl != null) {
                Media media = new Media(audioUrl.toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            } else {
                System.err.println("Could not locate alarm.mp3 in the resources folder.");
            }
        } catch (Exception e) {
            System.err.println("Error playing alarm audio: " + e.getMessage());
        }
    }

    @FXML
    private void onConfirmDestination() {
        try {
            double dLat = Double.parseDouble(destLatField.getText().trim());
            double dLng = Double.parseDouble(destLngField.getText().trim());
            pax.setDestination(dLat, dLng);
            pax.setBoarded(true);

            double fare = FareCalculator.calculateFare(
                    vehLat, vehLng, dLat, dLng, "Sedan / Toyota (5 seater)");
            fareLabel.setText("Estimated fare: " + FareCalculator.formatFare(fare));

            double etaSecs = ProximityEngine.estimateETA(vehLat, vehLng, dLat, dLng, 40);
            long   etaMins = (long)(etaSecs / 60);
            etaLabel.setText("ETA: ~" + etaMins + " min");

            startProximityCheck(dLat, dLng);
            statusLabel.setText("Ride booked! Proximity alarm is active.");
            statusLabel.setStyle("");
        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter valid coordinates.");
        }
    }

    @FXML
    private void onSetTimedAlarm() {
        try {
            int hr  = Integer.parseInt(alarmHrField.getText().trim());
            int min = Integer.parseInt(alarmMinField.getText().trim());
            if (hr < 0 || hr > 23 || min < 0 || min > 59) {
                alarmStatusLabel.setText("Enter a valid time (0-23 for hour, 0-59 for minute).");
                return;
            }
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
                showAlarmBanner("WAKE UP! You are " + (int) dist + "m from your stop!");
            }
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
}