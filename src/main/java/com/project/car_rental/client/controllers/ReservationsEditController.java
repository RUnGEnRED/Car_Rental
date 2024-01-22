package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Customer;
import com.project.car_rental.client.db.models.Reservation;
import com.project.car_rental.client.db.models.Vehicle;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tornadofx.control.DateTimePicker;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class is the controller for the reservation edit view.
 */
public class ReservationsEditController {

    private static Reservation selectedReservation;
    private static Vehicle selectedVehicle;
    private ObservableList<Vehicle> vehicleObservableList;
    private double price;

    @FXML private DateTimePicker rentalDate;
    @FXML private DateTimePicker returnDate;

    @FXML private ComboBox<String> chosenBrand;
    @FXML private ComboBox<String> chosenClass;

    @FXML private Label priceLabel;

    @FXML private TextField customerSearchBar;

    @FXML private TableView<Vehicle> vehicleTableView;

    @FXML private TableColumn<Vehicle, String> brandColumn;
    @FXML private TableColumn<Vehicle, String> modelColumn;
    @FXML private TableColumn<Vehicle, String> vehicleClassColumn;
    @FXML private TableColumn<Vehicle, Integer> yearOfProductionColumn;
    @FXML private TableColumn<Vehicle, String> transmissionColumn;
    @FXML private TableColumn<Vehicle, Integer> horsepowerColumn;
    @FXML private TableColumn<Vehicle, Double> fuelConsumptionColumn;
    @FXML private TableColumn<Vehicle, String> fuelTypeColumn;
    @FXML private TableColumn<Vehicle, Double> pricePerDayColumn;

    @FXML private TableView<Customer> customerTableView;

    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        vehicleClassColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleClass"));
        yearOfProductionColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfProduction"));
        transmissionColumn.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        horsepowerColumn.setCellValueFactory(new PropertyValueFactory<>("horsepower"));
        fuelConsumptionColumn.setCellValueFactory(new PropertyValueFactory<>("fuelConsumption"));
        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadReservation();

        readCustomerList();
        listenForAction();
    }

    /**
     * Switches to the main reservations scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("reservationsMain-view.fxml", event);
    }

    /**
     * Loads the selected reservation.
     */
    private void loadReservation() {
        selectedReservation = ReservationsMainController.getSelectedReservation();
        selectedVehicle = null;

        Date renDate = selectedReservation.getRentalDate();
        Date retDate = selectedReservation.getReturnDate();
        LocalDate renLocalDate = renDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate retLocalDate = retDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        rentalDate.setValue(renLocalDate);
        returnDate.setValue(retLocalDate);

        price = selectedReservation.getTotalCost();
        priceLabel.setText(String.format("New Price: %.2f", price));

        readVehiclesList();
        updateBrandAndClassList();
    }

    /**
     * Reads the customer list from the server.
     */
    private void readCustomerList() {
        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("readCustomerList", new Object());
        ObservableList<Customer> observableList = DataService.getObservableList(dataProcessor.getContent(), Customer.class);
        customerTableView.setItems(observableList);

        SortedList<Customer> sortedList = new CustomersService().searchCustomers(customerSearchBar, observableList);
        sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());
        customerTableView.setItems(sortedList);

        if(dataProcessor.getCommand().equals("customerList"))
            System.out.println("Customers has been loaded successfully");
    }

    /**
     * Reads the vehicles list from the server.
     */
    private void readVehiclesList() {
        if(rentalDate.getValue() == null || returnDate.getValue() == null)
            return;

        if(rentalDate.getValue().isAfter(returnDate.getValue())){
            vehicleTableView.setItems(FXCollections.observableArrayList());
            System.err.println("Invalid date frame");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid date frame.", "Incorrect data");
            return;
        }

        DataProcessor dataProcessor = new BookVehicleService().readVehicleListOnDateFrame(rentalDate, returnDate);
        vehicleObservableList = DataService.getObservableList(dataProcessor.getContent(), Vehicle.class);
        vehicleTableView.setItems(vehicleObservableList);

        if(dataProcessor.getCommand().equals("vehicleListOnDateFrame"))
            System.out.println("Vehicles has been loaded successfully");
    }

    /**
     * Updates the brand and class list.
     */
    private void updateBrandAndClassList() {
        ObservableList<String> brandObservableList = new BookVehicleService().createBrandList(vehicleTableView);
        ObservableList<String> vehicleClassObservableList = new BookVehicleService().createVehicleClassList(vehicleTableView);

        chosenBrand.setItems(brandObservableList);
        chosenClass.setItems(vehicleClassObservableList);
    }

    /**
     * Updates the vehicles list.
     */
    private void updateVehiclesList() {
        String selectedBrand = chosenBrand.getValue() == null ? "None" : String.valueOf(chosenBrand.getValue());
        String selectedClass = chosenClass.getValue() == null ? "None" : String.valueOf(chosenClass.getValue());

        ObservableList<Vehicle> filteredVehicles = vehicleObservableList;

        if (!selectedBrand.equals("None")) {
            filteredVehicles = filteredVehicles.filtered(vehicle -> vehicle.getBrand().equals(selectedBrand));
        }

        if (!selectedClass.equals("None")) {
            filteredVehicles = filteredVehicles.filtered(vehicle -> vehicle.getVehicleClass().equals(selectedClass));
        }

        vehicleTableView.setItems(filteredVehicles);
    }

    /**
     * Updates the price label.
     */
    private void updatePriceLabel() {
        if (selectedVehicle == null)
            return;

        LocalDateTime rentalDateTime = rentalDate.getDateTimeValue();
        LocalDateTime returnDateTime = returnDate.getDateTimeValue();

        Duration duration = Duration.between(rentalDateTime, returnDateTime);

        price = ((double) duration.toHours() / 24) * selectedVehicle.getPricePerDay();

        priceLabel.setText(String.format("New Price: %.2f", price));

        selectedReservation.setTotalCost(price);
    }

    /**
     * Listens for action.
     */
    private void listenForAction() {
        rentalDate.dateTimeValueProperty().addListener((observable, oldValue, newValue) -> {
            readVehiclesList();
            updateBrandAndClassList();
        });

        returnDate.dateTimeValueProperty().addListener((observable, oldValue, newValue) -> {
            readVehiclesList();
            updateBrandAndClassList();
        });

        chosenBrand.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateVehiclesList();
        });

        chosenClass.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateVehiclesList();
        });

        vehicleTableView.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectedReservation.setVehicleID(newValue == null ? selectedReservation.getVehicleID() : newValue.getVehicleID());
            selectedVehicle = (Vehicle) newValue;
            updatePriceLabel();
        });

        customerTableView.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectedReservation.setCustomerID(newValue == null ? selectedReservation.getCustomerID() : newValue.getCustomerID());
        });
    }

    /**
     * Updates a reservation.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void updateReservation(ActionEvent event) throws IOException {
        Reservation reservation = new BookVehicleService().convertDateTimeFormat(rentalDate, returnDate);
        reservation.setReservationID(selectedReservation.getReservationID());
        reservation.setCustomerID(selectedReservation.getCustomerID());
        reservation.setVehicleID(selectedReservation.getVehicleID());
        reservation.setTotalCost(selectedReservation.getTotalCost());

        if(!new BookVehicleService().validateReservationData(reservation))
            return;

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("updateReservation", reservation);

        if(dataProcessor.getCommand().equals("reservationUpdated")) {
            System.out.println("Reservation has been updated successfully");
            SceneService.showAlertBox(Alert.AlertType.INFORMATION,"Alert Car Rental", "Reservation has been updated successfully.", "Procedure complete");
            SceneService.switchToScene("reservationsMain-view.fxml", event);
        }
    }
}
