package com.oop.voyage.project_voyage;

import com.oop.voyage.project_voyage.model.Passenger;

//These files are yet to be added
import com.oop.voyage.project_voyage.services.AlarmService;
import com.oop.voyage.project_voyage.services.FareCalculator;
import com.oop.voyage.project_voyage.services.ProximityEngine;

//Javafx libraries
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

    @FXML
    private Label statusLabel;
    @FXML
    private Label fareLabel;
    @FXML
    private Label etaLabel;
    @FXML
    private Label alarmStatusLabel;
    @FXML
    private Label seatsLabel;
    @FXML
    private TextField destLatField;
    @FXML
    private TextField destLngField;
    @FXML
    private TextField alarmHrField;
    @FXML
    private TextField alarmMinField;

    private Passenger pax;
    private AlarmService alarmSvc;
    private boolean proximAlarmFired = false;

    // simulated vehicle position
    private double vehLat = 33.1450;
    private double vehLng = 73.7510;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alarmSvc = new AlarmService();
    }
}