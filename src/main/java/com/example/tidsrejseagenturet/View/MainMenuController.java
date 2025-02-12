package com.example.tidsrejseagenturet.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button customerAdminButton;
    @FXML
    private Button timeMachineButton;
    @FXML
    private Button timePeriodsButton;
    @FXML
    private Button bookingButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleCustomerAdmin() {
        try {
            loadScene("customerAdmin.fxml", "Kunde Administration");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Kunne ikke åbne kunde administration");
        }
    }

    @FXML
    private void handleTimeMachines() {
        try {
            loadScene("timeMachines.fxml", "Tidsrejsemaskiner");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Kunne ikke åbne tidsrejsemaskiner");
        }
    }

    @FXML
    private void handleTimePeriods() {
        try {
            loadScene("timePeriods.fxml", "Tidsperioder");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Kunne ikke åbne tidsperioder");
        }
    }

    @FXML
    private void handleBooking() {
        try {
            loadScene("booking.fxml", "Book Tidsrejse");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Kunne ikke åbne booking");
        }
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void loadScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tidsrejseagenturet/" + fxmlFile));
        Parent root = loader.load();

        Stage stage = (Stage) customerAdminButton.getScene().getWindow();
        stage.setTitle("Tidsrejseagenturet - " + title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showError(String message) {
        System.err.println(message);
        // You can implement a proper error dialog here if needed
    }
}