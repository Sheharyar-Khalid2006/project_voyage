package com.oop.voyage.project_voyage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    public StackPane rootPane;
    @FXML private VBox      formCard;
    @FXML private TextField cnicField;
    @FXML private TextField phoneField;
    @FXML private TextField gmailField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nothing to set up yet
    }

    public void initRole(String role) {
    }

        @FXML
    private void onContinueClicked() {
    }
}
