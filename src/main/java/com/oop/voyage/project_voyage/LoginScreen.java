package com.oop.voyage.project_voyage;

import com.oop.voyage.project_voyage.model.Driver;
import com.oop.voyage.project_voyage.model.Passenger;
import com.oop.voyage.project_voyage.model.Car;
import com.oop.voyage.project_voyage.services.RideSession;
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

    @FXML private VBox   formCard;
    @FXML private Label  topBadgeLabel;
    @FXML private Label  roleIconLabel;
    @FXML private Label  roleTitleLabel;
    @FXML private Label  roleSubtitleLabel;
    @FXML private Label  errLabel;

    @FXML private TextField        cnicField;
    @FXML private TextField        phoneField;
    @FXML private TextField        gmailField;
    @FXML private VBox             driverSection;
    @FXML private ComboBox<String> carTypeCombo;
    @FXML private TextField        plateField;

    private String role;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carTypeCombo.getItems().addAll(
                "Sedan / Toyota (5 seater)",
                "SUV / Crossover (7 seater)",
                "Van / Minivan",
                "Mazda / Bus",
                "Motorcycle",
                "DoubleDecker Bus"
        );
    }

    public void initRole(String r) {
        this.role = r;
        if (ROLE_DRIVER.equals(r)) {
            roleIconLabel.setText("🚘");
            roleTitleLabel.setText("Driver Login");
            roleSubtitleLabel.setText("Enter your details to start offering rides");
            topBadgeLabel.setText("Voyage • Driver");
            driverSection.setVisible(true);
            driverSection.setManaged(true);
        } else {
            Image img = new Image(getClass().getResourceAsStream(
                    "/com/oop/voyage/project_voyage/passenger.png"));
            ImageView iv = new ImageView(img);
            iv.setFitHeight(40);
            iv.setFitWidth(40);
            iv.setPreserveRatio(true);
            roleIconLabel.setText("");
            roleIconLabel.setGraphic(iv);
            roleTitleLabel.setText("Login");
            roleSubtitleLabel.setText("Enter your details to book a ride");
            topBadgeLabel.setText("Voyage • Passenger");
            driverSection.setVisible(false);
            driverSection.setManaged(false);
        }
    }

    @FXML
    private void onContinueClicked() {
        String cnic  = cnicField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = gmailField.getText().trim();

        if (cnic.isEmpty() || phone.isEmpty()) {
            showErr("CNIC and phone number are required.");
            return;
        }

        if (ROLE_DRIVER.equals(role)) {
            String carTyp = carTypeCombo.getValue();
            String plt    = plateField.getText().trim().toUpperCase();
            if (carTyp == null || plt.isEmpty()) {
                showErr("Please select a car type and enter plate number.");
                return;
            }
            Driver  drv  = new Driver(cnic, phone, email, carTyp, plt);
            Car     car  = new Car(carTyp, plt);
            RideSession ses = new RideSession(drv, car);
            loadDriverDashboard(ses);
        } else {
            Passenger pax = new Passenger(cnic, phone, email);
            loadPassengerView(pax);
        }
    }

    private void showErr(String msg) {
        if (errLabel != null) {
            errLabel.setText(msg);
            errLabel.setVisible(true);
        }
    }

    private void loadDriverDashboard(RideSession ses) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource(
                    "/com/oop/voyage/project_voyage/DriverDashboard.fxml"));
            Scene sc = new Scene(ldr.load(),
                    Voyage.primaryStage.getWidth(),
                    Voyage.primaryStage.getHeight());
            sc.getStylesheets().add(getClass().getResource(
                    "/com/oop/voyage/project_voyage/Styles.css").toExternalForm());
            DriverDashboard ctrl = ldr.getController();
            ctrl.initSession(ses);
            Voyage.primaryStage.setScene(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadPassengerView(Passenger pax) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource(
                    "/com/oop/voyage/project_voyage/PassengerView.fxml"));
            Scene sc = new Scene(ldr.load(),
                    Voyage.primaryStage.getWidth(),
                    Voyage.primaryStage.getHeight());
            sc.getStylesheets().add(getClass().getResource(
                    "/com/oop/voyage/project_voyage/Styles.css").toExternalForm());
            PassengerView ctrl = ldr.getController();
            ctrl.initPassenger(pax);
            Voyage.primaryStage.setScene(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onBackClicked() {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource(
                    "/com/oop/voyage/project_voyage/StartingScreen.fxml"));
            Scene sc = new Scene(ldr.load(),
                    Voyage.primaryStage.getWidth(),
                    Voyage.primaryStage.getHeight());
            sc.getStylesheets().add(getClass().getResource(
                    "/com/oop/voyage/project_voyage/Styles.css").toExternalForm());
            Voyage.primaryStage.setScene(sc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}