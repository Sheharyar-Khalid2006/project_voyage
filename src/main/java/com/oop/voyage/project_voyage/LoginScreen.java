package com.oop.voyage.project_voyage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    public static final String ROLE_DRIVER    = "DRIVER";
    public static final String ROLE_PASSENGER = "PASSENGER";

    public StackPane rootPane;

    @FXML private VBox formCard;
    @FXML private Label topBadgeLabel;

    //Labels
    @FXML private Label roleIconLabel;
    @FXML private Label roleTitleLabel;
    @FXML private Label roleSubtitleLabel;

    //Common Fields
    @FXML private TextField cnicField;
    @FXML private TextField phoneField;
    @FXML private TextField gmailField;

    @FXML private VBox driverSection;
    @FXML private ComboBox<String> carTypeCombo;
    @FXML private TextField plateField;

    private String role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupCarTypes();
    }

    public void initRole(String role) {
        this.role = role;

        if (ROLE_DRIVER.equals(role)) {
            roleIconLabel.setText("🚘");
            roleTitleLabel.setText("Driver Login");
            roleSubtitleLabel.setText("Enter your details to start offering rides");
            topBadgeLabel.setText("Voyage • Driver");
            driverSection.setVisible(true);
            driverSection.setManaged(true);
        } else {
            roleIconLabel.setText("");
            Image passengerIcon = new Image(getClass().getResourceAsStream("/com/oop/voyage/project_voyage/passenger.png"));
            ImageView imageView = new ImageView(passengerIcon);

            // Adjust size to match your UI needs
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            imageView.setPreserveRatio(true);

            roleIconLabel.setGraphic(imageView);
            roleTitleLabel.setText("Passenger Login");
            roleSubtitleLabel.setText("Enter your details to start booking rides");
            topBadgeLabel.setText("Voyage • Passenger");
            driverSection.setVisible(false);
            driverSection.setManaged(false);
        }
    }

    private void setupCarTypes() {
        carTypeCombo.getItems().addAll(
                "Sedan / Toyota (5 seater)",
                "SUV / Crossover (7 seater)",
                "Van / Minivan",
                "Mazda / Bus",
                "Motorcycle",
                "DoubleDecker Bus"
        );
    }

    public void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(
                    getClass().getResource("/com/oop/voyage/project_voyage/Styles.css")
                            .toExternalForm());
            Voyage.primaryStage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadStartScreen() {
        loadScene("/com/oop/voyage/project_voyage/StartScreen.fxml");
    }

    @FXML
    private void onBackClicked() {
        loadStartScreen();
    }

        @FXML
    private void onContinueClicked() {
        //Validation and Navigation will be added later
    }
}
