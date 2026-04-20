package com.oop.voyage.project_voyage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.util.Duration;
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
        responsiveSizes();
    }

    private void responsiveSizes() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        double cardW = clamp(screen.getWidth()  * 0.15, 200, 320);
        double cardH = clamp(screen.getHeight() * 0.40, 260, 420);

        for (VBox card : new VBox[]{driverCard, passengerCard}) {
            card.setPrefWidth(cardW);
            card.setPrefHeight(cardH);
            card.setMinWidth(cardW);
            card.setMinHeight(cardH);
        }

        double titlePx = clamp(screen.getWidth() * 0.04, 30, 60);
        appTitleLabel.setStyle("-fx-font-size: " + titlePx + "px;");

        double iconPx = clamp(screen.getWidth() * 0.05, 35, 80);
        logoIconLabel.setStyle("-fx-font-size: " + iconPx + "px;");

        double gap = clamp(screen.getWidth() * 0.05, 40, 100);
        cardsRow.setSpacing(gap);
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
        scaleCard(driverCard, 1.05);
    }

    @FXML
    private void onDriverUnhover(MouseEvent e) {
        scaleCard(driverCard, 1.00);
    }

    @FXML
    private void onPassengerHover(MouseEvent e) {
        scaleCard(passengerCard, 1.05);
    }

    @FXML
    private void onPassengerUnhover(MouseEvent e) {
        scaleCard(passengerCard, 1.00);
    }

    private void scaleCard(VBox card, double factor) {
        ScaleTransition st = new ScaleTransition(Duration.millis(250), card);
        st.setToX(factor);
        st.setToY(factor);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.play();
    }

    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}