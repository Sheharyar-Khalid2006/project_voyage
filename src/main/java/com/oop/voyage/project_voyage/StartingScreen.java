package com.oop.voyage.project_voyage;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.util.Duration;
import java.io.IOException;
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

    private static final String ROLE_DRIVER    = "DRIVER";
    private static final String ROLE_PASSENGER = "PASSENGER";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        responsiveSizes();
        playEntryAnimation();
    }

    private void responsiveSizes() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        double cardW = clamp(screen.getWidth()  * 0.25, 200, 320);
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

    private void playEntryAnimation() {
        contentBox.setOpacity(0);
        contentBox.setTranslateY(50);

        FadeTransition     fade  = new FadeTransition(Duration.millis(750), contentBox);
        TranslateTransition slide = new TranslateTransition(Duration.millis(750), contentBox);

        fade.setFromValue(0);
        fade.setToValue(1);

        slide.setFromY(50);
        slide.setToY(0);
        slide.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition entry = new ParallelTransition(fade, slide);
        entry.setDelay(Duration.millis(120));
        entry.play();
    }

    @FXML
    private void onDriverCardClicked(MouseEvent e) {
        loadLoginScreen(ROLE_DRIVER);
    }

    @FXML
    private void onPassengerCardClicked(MouseEvent e) {
        loadLoginScreen(ROLE_PASSENGER);
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

    private void loadLoginScreen(String role) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/oop/voyage/project_voyage/LoginScreen.fxml"));

            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(
                    getClass().getResource("/com/oop/voyage/project_voyage/styles.css").toExternalForm());

            LoginScreen ctrl = loader.getController();
            ctrl.initRole(role);

            Voyage.primaryStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}