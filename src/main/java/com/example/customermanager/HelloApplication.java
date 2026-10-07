package com.example.customermanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class HelloApplication extends Application {

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Customer Name:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter full name");

        Label provinceLabel = new Label("Province:");
        ComboBox<String> provinceComboBox = new ComboBox<>();
        provinceComboBox.getItems().addAll(
                "Central", "Copperbelt", "Eastern", "Luapula",
                "Lusaka", "Muchinga", "Northern", "North-Western",
                "Southern", "Western"
        );
        provinceComboBox.setPromptText("Select Province");

        Button addButton = new Button("Add Customer");
        Button deleteButton = new Button("Delete Selected");

        TableView<Customer> table = new TableView<>();
        table.setItems(customerList);

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(200);

        TableColumn<Customer, String> provinceColumn = new TableColumn<>("Province");
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        provinceColumn.setPrefWidth(150);

        table.getColumns().add(nameColumn);
        table.getColumns().add(provinceColumn);

        addButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            String province = provinceComboBox.getValue();

            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Name field cannot be empty.");
                return;
            }

            if (province == null || province.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a province.");
                return;
            }

            customerList.add(new Customer(name, province));
            nameInput.clear();
            provinceComboBox.getSelectionModel().clearSelection();
        });

        deleteButton.setOnAction(e -> {
            Customer selectedCustomer = table.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                showAlert(Alert.AlertType.WARNING, "Selection Required", "Please select a customer to delete.");
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete customer: " + selectedCustomer.getName() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                customerList.remove(selectedCustomer);
            }
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameInput, 1, 0);
        formGrid.add(provinceLabel, 0, 1);
        formGrid.add(provinceComboBox, 1, 1);

        HBox buttonBox = new HBox(10, addButton, deleteButton);
        VBox root = new VBox(15, formGrid, buttonBox, table);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Customer Manager Lab");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}