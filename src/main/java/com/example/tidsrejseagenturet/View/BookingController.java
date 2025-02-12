package com.example.tidsrejseagenturet.View;

import com.example.tidsrejseagenturet.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BookingController {
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private ComboBox<TimePeriods> timePeriodComboBox;
    @FXML
    private ComboBox<TimeMachine> timeMachineComboBox;
    @FXML
    private ComboBox<Guide> guideComboBox;
    @FXML
    private Button createBookingButton;

    private CustomerDatabaseHandler customerHandler = new CustomerDatabaseHandler();
    private TimePeriodsDatabaseHandler timePeriodsHandler = new TimePeriodsDatabaseHandler();
    private TimeMachineDatabaseHandler timeMachineHandler = new TimeMachineDatabaseHandler();
    private GuideDatabaseHandler guideHandler = new GuideDatabaseHandler();
    private BookingDatabaseHandler bookingHandler = new BookingDatabaseHandler();

    @FXML
    public void initialize() {
        loadComboBoxData();
        setupComboBoxDisplays();
    }

    private void loadComboBoxData() {
        // Load all data
        customerComboBox.setItems(FXCollections.observableArrayList(customerHandler.getAllCustomersFromDB()));
        timePeriodComboBox.setItems(FXCollections.observableArrayList(timePeriodsHandler.getAllTimePeriodsFromDB()));
        timeMachineComboBox.setItems(FXCollections.observableArrayList(timeMachineHandler.getAllTimeMachinesFromDB()));
        guideComboBox.setItems(guideHandler.getAllGuides());
    }

    private void setupComboBoxDisplays() {
        // Set how each object should be displayed in the ComboBoxes
        customerComboBox.setConverter(new javafx.util.StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getName() + " (" + customer.getEmail() + ")" : "";
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });

        timePeriodComboBox.setConverter(new javafx.util.StringConverter<TimePeriods>() {
            @Override
            public String toString(TimePeriods period) {
                return period != null ? period.getName() : "";
            }

            @Override
            public TimePeriods fromString(String string) {
                return null;
            }
        });

        timeMachineComboBox.setConverter(new javafx.util.StringConverter<TimeMachine>() {
            @Override
            public String toString(TimeMachine machine) {
                return machine != null ? machine.getName() + " (Kapacitet: " + machine.getCapacity() + ")" : "";
            }

            @Override
            public TimeMachine fromString(String string) {
                return null;
            }
        });

        guideComboBox.setConverter(new javafx.util.StringConverter<Guide>() {
            @Override
            public String toString(Guide guide) {
                return guide != null ? guide.getName() + " (" + guide.getSpecialty() + ")" : "";
            }

            @Override
            public Guide fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleCreateBooking() {
        if (!validateSelections()) {
            return;
        }

        Customer selectedCustomer = customerComboBox.getValue();
        TimePeriods selectedPeriod = timePeriodComboBox.getValue();
        TimeMachine selectedMachine = timeMachineComboBox.getValue();
        Guide selectedGuide = guideComboBox.getValue();

        // Create booking without checking status
        Booking newBooking = bookingHandler.createBooking(
                selectedCustomer.getId(),
                selectedMachine.getId(),
                selectedPeriod.getId(),
                selectedGuide.getId()
        );

        if (newBooking != null) {
            showSuccess("Booking oprettet!", "Din tidsrejse er nu booket");
            returnToMainMenu();
        } else {
            showAlert("Fejl", "Kunne ikke oprette booking");
        }
    }

    private boolean validateSelections() {
        if (customerComboBox.getValue() == null) {
            showAlert("Fejl", "Vælg venligst en kunde");
            return false;
        }
        if (timePeriodComboBox.getValue() == null) {
            showAlert("Fejl", "Vælg venligst en tidsperiode");
            return false;
        }
        if (timeMachineComboBox.getValue() == null) {
            showAlert("Fejl", "Vælg venligst en tidsmaskine");
            return false;
        }
        if (guideComboBox.getValue() == null) {
            showAlert("Fejl", "Vælg venligst en guide");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccess(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void returnToMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tidsrejseagenturet/mainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) createBookingButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Fejl", "Kunne ikke vende tilbage til hovedmenuen");
        }
    }
}