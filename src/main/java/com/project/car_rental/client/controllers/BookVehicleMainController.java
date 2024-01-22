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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tornadofx.control.DateTimePicker;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * BookVehicleMainController is a class that handles the actions on the main vehicle booking view.
 */
public class BookVehicleMainController {

    private static Customer selectedCustomer;
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
     * Initializes the main vehicle booking view by reading the customer list.
     */
    @FXML
    private void initialize() {
        rentalDate.setValue(null);
        returnDate.setValue(null);
        selectedCustomer = null;
        selectedVehicle = null;
        price = 0;

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

        readCustomerList();
        listenForAction();
    }

    /**
     * Switches the scene to the main car rental view.
     *
     * @param event The action event that triggered the scene switch.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("carRentalMain-view.fxml", event);
    }

    /**
     * Reads the customer list from the server and sets it to the customer table view.
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
     * Reads the vehicles list from the server and sets it to the vehicle table view.
     */
    private void readVehiclesList() {
        if(rentalDate.getValue() == null || returnDate.getValue() == null)
            return;

        if(rentalDate.getValue().isAfter(returnDate.getValue())){
            vehicleTableView.setItems(FXCollections.observableArrayList());
            System.err.println("Invalid date frame");
            SceneService.showAlertBox(AlertType.WARNING,"Alert Car Rental", "Invalid date frame.", "Incorrect data");
            return;
        }

        DataProcessor dataProcessor = new BookVehicleService().readVehicleListOnDateFrame(rentalDate, returnDate);
        vehicleObservableList = DataService.getObservableList(dataProcessor.getContent(), Vehicle.class);
        vehicleTableView.setItems(vehicleObservableList);

        if(dataProcessor.getCommand().equals("vehicleListOnDateFrame"))
            System.out.println("Vehicles has been loaded successfully");
    }

    /**
     * Updates the brand and class list in the view.
     */
    private void updateBrandAndClassList() {
        ObservableList<String> brandObservableList = new BookVehicleService().createBrandList(vehicleTableView);
        ObservableList<String> vehicleClassObservableList = new BookVehicleService().createVehicleClassList(vehicleTableView);

        chosenBrand.setItems(brandObservableList);
        chosenClass.setItems(vehicleClassObservableList);
    }

    /**
     * Updates the vehicles list in the view based on the selected brand and class.
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
     * Updates the price label in the view based on the selected vehicle and the rental and return dates.
     */
    private void updatePriceLabel() {
        if (selectedVehicle == null)
            return;

        LocalDateTime rentalDateTime = rentalDate.getDateTimeValue();
        LocalDateTime returnDateTime = returnDate.getDateTimeValue();

        Duration duration = Duration.between(rentalDateTime, returnDateTime);

        price = ((double) duration.toHours() / 24) * selectedVehicle.getPricePerDay();

        priceLabel.setText(String.format("Price: %.2f", price));
    }

    /**
     * Listens for action on the view controls and updates the view accordingly.
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
            selectedVehicle = (Vehicle) newValue;
            updatePriceLabel();
        });

        customerTableView.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            selectedCustomer = (Customer) newValue;
        });
    }

    /**
     * Creates a reservation based on the selected customer and vehicle and the rental and return dates.
     *
     * @param event The action event that triggered the reservation creation.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void createReservation(ActionEvent event) throws IOException {
        Reservation reservation = new BookVehicleService().convertDateTimeFormat(rentalDate, returnDate);
        reservation.setCustomerID(selectedCustomer == null ? 0 : selectedCustomer.getCustomerID());
        reservation.setVehicleID(selectedVehicle == null ? 0 : selectedVehicle.getVehicleID());
        reservation.setTotalCost(price);

        if(!new BookVehicleService().validateReservationData(reservation))
            return;

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("createReservation", reservation);

        if(dataProcessor.getCommand().equals("reservationCreated")) {
            System.out.println("Reservation has been added successfully");
            SceneService.showAlertBox(AlertType.INFORMATION,"Alert Car Rental", "Reservation has been added successfully.", "Procedure complete");
            SceneService.switchToScene("carRentalMain-view.fxml", event);
        }
    }
}
