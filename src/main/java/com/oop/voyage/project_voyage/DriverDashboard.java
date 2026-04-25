package com.oop.voyage.project_voyage;

//These files are yet to be added and integrated with this file
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

    @FXML
    private Label plateLabel;
    @FXML
    private Label carTypLabel;
    @FXML
    private Label seatsLabel;
    @FXML
    private Label etaLabel;
    @FXML
    private Label fareLabel;
    @FXML
    private Label speedLabel;
    @FXML
    private Label notifLabel;
    @FXML
    private TextField destLatField;
    @FXML
    private TextField destLngField;
    @FXML
    private ListView<String> paxList;

    private RideSession ses;

    // simulated GPS coords — Mirpur AJK area as starting point
    private double simLat = 33.1450;
    private double simLng = 73.7510;
    private double simSpd = 40.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}