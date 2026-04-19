package com.oop.voyage.project_voyage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StartingScreen implements Initializable {

    @FXML private StackPane rootPane;
    @FXML private VBox      contentBox;
    @FXML private HBox      cardsRow;
    @FXML private VBox      driverCard;
    @FXML private VBox      passengerCard;
    @FXML private Label     logoIconLabel;
    @FXML private Label     appTitleLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onDriverCardClicked(MouseEvent e) {
        System.out.println("Driver selected - Navigation pending");
    }

    @FXML
    private void onPassengerCardClicked(MouseEvent e) {
        System.out.println("Hello Passenger - Navigation pending");
    }

    @FXML
    private void onDriverHover(MouseEvent e) {
        // Animation placeholder
    }

    @FXML
    private void onDriverUnhover(MouseEvent e) {
        // Animation placeholder
    }

    @FXML
    private void onPassengerHover(MouseEvent e) {
        // Animation placeholder
    }

    @FXML
    private void onPassengerUnhover(MouseEvent e) {
        // Animation placeholder
    }
}