package com.oop.voyage.project_voyage;

import com.oop.voyage.project_voyage.model.Passenger;
import com.oop.voyage.project_voyage.services.FareCalculator;
import com.oop.voyage.project_voyage.services.RideSession;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverDashboard implements Initializable {

    @FXML private Label    plateLabel;
    @FXML private Label    carTypLabel;
    @FXML private Label    seatsLabel;
    @FXML private Label    etaLabel;
    @FXML private Label    fareLabel;
    @FXML private Label    speedLabel;
    @FXML private Label    notifLabel;
    @FXML private TextField destLatField;
    @FXML private TextField destLngField;
    @FXML private ListView<String> paxList;

    private RideSession ses;

    // simulated GPS coords — Mirpur AJK area as starting point
    private double simLat = 33.1450;
    private double simLng = 73.7510;
    private double simSpd = 40.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void initSession(RideSession s) {
        this.ses = s;
        plateLabel.setText(ses.getVehicle().getNumberPlate());
        carTypLabel.setText(ses.getVehicle().getVehicleType());
        seatsLabel.setText(String.valueOf(ses.getAvailableSeats()));
        startGpsSim();
    }

    private void startGpsSim() {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            simLat += 0.0008;
            simLng += 0.0005;
            ses.updateLocation(simLat, simLng, simSpd);
            refreshEta();
            seatsLabel.setText(String.valueOf(ses.getAvailableSeats()));
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    private void refreshEta() {
        try {
            double dLat = Double.parseDouble(destLatField.getText().trim());
            double dLng = Double.parseDouble(destLngField.getText().trim());
            etaLabel.setText(ses.getFormattedETA(dLat, dLng));
            double fare = FareCalculator.calculateFare(
                    simLat, simLng, dLat, dLng,
                    ses.getVehicle().getVehicleType());
            fareLabel.setText(FareCalculator.formatFare(fare));
        } catch (NumberFormatException ex) {
            etaLabel.setText("Enter destination");
        }
    }

    @FXML
    private void onSetDestination() {
        refreshEta();
    }

    @FXML
    private void onWakePassengers() {
        if (ses.getPassengers().isEmpty()) {
            notifLabel.setText("No passengers onboard.");
            return;
        }
        for (Passenger pax : ses.getPassengers()) {
            ses.getDriver().wakePassenger(pax);
        }
        notifLabel.setText("Wake alert sent to all passengers.");
    }

    @FXML
    private void onAddTestPassenger() {
        Passenger pax = new Passenger("00000-0000000-0", "0300-0000000", "");
        pax.setDestination(33.1620, 73.7650);
        boolean boarded = ses.addPassenger(pax);
        if (boarded) {
            paxList.getItems().add(pax.getPublicInfo());
            seatsLabel.setText(String.valueOf(ses.getAvailableSeats()));
            notifLabel.setText("Passenger boarded.");
        } else {
            notifLabel.setText("Vehicle is full.");
        }
    }

    @FXML
    private void onEndRide() {
        ses.endRide();
        notifLabel.setText("Ride ended.");
    }
}