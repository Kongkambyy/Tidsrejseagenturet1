package com.example.tidsrejseagenturet.View;

import com.example.tidsrejseagenturet.Model.Customer;
import com.example.tidsrejseagenturet.Model.CustomerDatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CustomerAdminController {
    @FXML
    private ListView<String> idListView;
    @FXML
    private ListView<String> nameListView;
    @FXML
    private ListView<String> emailListView;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private ComboBox<String> menuComboBox;

    private CustomerDatabaseHandler dbHandler = new CustomerDatabaseHandler();
    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupMenuComboBox();
        setupListViewListeners();
        loadCustomers();
    }

    private void setupMenuComboBox() {
        menuComboBox.setItems(FXCollections.observableArrayList(
                "Hovedmenu",
                "Tidsrejsemaskiner",
                "Tidsperioder",
                "Book Tidsrejse"
        ));
    }

    private void setupListViewListeners() {
        // Synchronize selection between lists
        idListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                nameListView.getSelectionModel().select(newVal.intValue());
                emailListView.getSelectionModel().select(newVal.intValue());
                if (newVal.intValue() >= 0) {
                    Customer selected = customers.get(newVal.intValue());
                    nameField.setText(selected.getName());
                    emailField.setText(selected.getEmail());
                }
            }
        });

        nameListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idListView.getSelectionModel().select(newVal.intValue());
                emailListView.getSelectionModel().select(newVal.intValue());
            }
        });

        emailListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idListView.getSelectionModel().select(newVal.intValue());
                nameListView.getSelectionModel().select(newVal.intValue());
            }
        });
    }

    private void loadCustomers() {
        customers.clear();
        customers.addAll(dbHandler.getAllCustomersFromDB());
        updateListViews();
    }

    private void updateListViews() {
        ObservableList<String> ids = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();
        ObservableList<String> emails = FXCollections.observableArrayList();

        for (Customer customer : customers) {
            ids.add(customer.getId().toString());
            names.add(customer.getName());
            emails.add(customer.getEmail());
        }

        idListView.setItems(ids);
        nameListView.setItems(names);
        emailListView.setItems(emails);
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            showAlert("Fejl", "Alle felter skal udfyldes");
            return;
        }

        Customer newCustomer = dbHandler.createCustomerInDB(name, email);
        if (newCustomer != null) {
            loadCustomers();
            clearFields();
        } else {
            showAlert("Fejl", "Kunne ikke oprette kunden");
        }
    }

    @FXML
    private void handleEdit() {
        int selectedIndex = idListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("Fejl", "Vælg en kunde at redigere");
            return;
        }

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            showAlert("Fejl", "Alle felter skal udfyldes");
            return;
        }

        Customer selectedCustomer = customers.get(selectedIndex);
        if (dbHandler.editCustomerInDB(selectedCustomer.getId(), name, email)) {
            loadCustomers();
            clearFields();
        } else {
            showAlert("Fejl", "Kunne ikke opdatere kunden");
        }
    }

    @FXML
    private void handleRemove() {
        int selectedIndex = idListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            showAlert("Fejl", "Vælg en kunde at fjerne");
            return;
        }

        Customer selectedCustomer = customers.get(selectedIndex);
        if (dbHandler.deleteCustomerFromDB(selectedCustomer.getId())) {
            loadCustomers();
            clearFields();
        } else {
            showAlert("Fejl", "Kunne ikke slette kunden");
        }
    }

    @FXML
    private void handleMenuSelect() {
        String selected = menuComboBox.getValue();
        if (selected == null) return;

        String fxmlFile = switch (selected) {
            case "Hovedmenu" -> "mainMenu.fxml";
            case "Tidsrejsemaskiner" -> "timeMachines.fxml";
            case "Tidsperioder" -> "timePeriods.fxml";
            case "Book Tidsrejse" -> "booking.fxml";
            default -> null;
        };

        if (fxmlFile != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tidsrejseagenturet/" + fxmlFile));
                Parent root = loader.load();
                Stage stage = (Stage) menuComboBox.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Fejl", "Kunne ikke åbne " + selected);
            }
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        idListView.getSelectionModel().clearSelection();
        nameListView.getSelectionModel().clearSelection();
        emailListView.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}