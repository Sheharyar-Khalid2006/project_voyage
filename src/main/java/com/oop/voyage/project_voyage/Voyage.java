package com.oop.voyage.project_voyage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class Voyage extends Application {

    public static Stage primaryStage;   // ← add this

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader(
                Voyage.class.getResource("StartingScreen.fxml"));

        Scene scene = new Scene(loader.load(), screen.getWidth(), screen.getHeight());
        scene.getStylesheets().add(
                Voyage.class.getResource("Styles.css").toExternalForm());

        stage.setTitle("Voyage – Your Travel Companion");
        stage.setScene(scene);
        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setMaximized(true);
        stage.show();
    }
}