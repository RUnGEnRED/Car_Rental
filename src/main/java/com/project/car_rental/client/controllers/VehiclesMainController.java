package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Vehicle;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.ConnectionService;
import com.project.car_rental.client.services.DataService;
import com.project.car_rental.client.services.SceneService;
import com.project.car_rental.client.services.VehicleService;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Controller class for the main vehicle view in the Car Rental application.
 */
public class VehiclesMainController {

    private static Vehicle selectedVehicle;

    @FXML private TextField vehicleSearchBar;

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

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        selectedVehicle = null;

        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        vehicleClassColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleClass"));
        yearOfProductionColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfProduction"));
        transmissionColumn.setCellValueFactory(new PropertyValueFactory<>("transmission"));
        horsepowerColumn.setCellValueFactory(new PropertyValueFactory<>("horsepower"));
        fuelConsumptionColumn.setCellValueFactory(new PropertyValueFactory<>("fuelConsumption"));
        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));

        readVehicleList();
    }

    /**
     * Switches to the main Car Rental scene.
     *
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("carRentalMain-view.fxml", event);
    }

    /**
     * Switches to the Add Vehicles scene.
     *
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    public void switchToSceneVehiclesAdd(ActionEvent event) throws IOException {
        SceneService.switchToScene("vehiclesAdd-view.fxml", event);
    }

    /**
     * Switches to the Edit Vehicles scene.
     *
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void switchToSceneVehiclesEdit(ActionEvent event) throws IOException {
        selectedVehicle = vehicleTableView.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            System.err.println("No vehicle selected");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "", "No vehicle selected.");
        } else {
            System.out.println("Selected vehicle: " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
            SceneService.switchToScene("vehiclesEdit-view.fxml", event);
        }
    }

    /**
     * Reads the list of vehicles from the server and displays them in the table view.
     */
    private void readVehicleList() {
        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("readVehicleList", new Object());
        ObservableList<Vehicle> observableList = DataService.getObservableList(dataProcessor.getContent(), Vehicle.class);
        vehicleTableView.setItems(observableList);

        SortedList<Vehicle> sortedList = new VehicleService().searchVehicles(vehicleSearchBar, observableList);
        sortedList.comparatorProperty().bind(vehicleTableView.comparatorProperty());
        vehicleTableView.setItems(sortedList);

        if(dataProcessor.getCommand().equals("customerList"))
            System.out.println("Customers has been loaded successfully");
    }

    /**
     * Gets the selected vehicle.
     *
     * @return The selected vehicle.
     */
    public static Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }
}
