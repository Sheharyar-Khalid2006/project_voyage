package com.oop.voyage.project_voyage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class StartingScreen implements Initializable {

    @FXML private StackPane rootPane;
    @FXML private VBox      contentBox;
    @FXML private Label     logoIconLabel;
    @FXML private Label     appTitleLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}