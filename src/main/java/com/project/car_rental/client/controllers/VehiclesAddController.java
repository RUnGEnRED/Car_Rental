package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Vehicle;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.ConnectionService;
import com.project.car_rental.client.services.SceneService;
import com.project.car_rental.client.services.VehicleService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the Add Vehicles view in the Car Rental application.
 */
public class VehiclesAddController {

    @FXML private TextField brand;
    @FXML private TextField model;
    @FXML private TextField vehicleClass;
    @FXML private TextField yearOfProduction;
    @FXML private TextField transmission;
    @FXML private TextField horsepower;
    @FXML private TextField fuelConsumption;
    @FXML private TextField fuelType;
    @FXML private TextField pricePerDay;

    /**
     * Switches to the main Vehicles scene.
     *
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    public void switchToSceneVehiclesMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("vehiclesMain-view.fxml", event);
    }

    /**
     * Creates a new vehicle and sends it to the server.
     *
     * @param event The event that triggered the vehicle creation.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void createVehicle(ActionEvent event) throws IOException {
        if(!new VehicleService().validateVehiclesData(brand.getText(), model.getText(), vehicleClass.getText(),
                yearOfProduction.getText(), transmission.getText(), horsepower.getText(),
                fuelConsumption.getText(), fuelType.getText(), pricePerDay.getText()))
        {
            return;
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand.getText());
        vehicle.setModel(model.getText());
        vehicle.setVehicleClass(vehicleClass.getText());
        vehicle.setYearOfProduction(Integer.parseInt(yearOfProduction.getText()));
        vehicle.setTransmission(transmission.getText());
        vehicle.setHorsepower(Integer.parseInt(horsepower.getText()));
        vehicle.setFuelConsumption(Double.parseDouble(fuelConsumption.getText()));
        vehicle.setFuelType(fuelType.getText());
        vehicle.setPricePerDay(Double.parseDouble(pricePerDay.getText()));

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("createVehicle", vehicle);

        if(dataProcessor.getCommand().equals("vehicleCreated")) {
            System.out.println("Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been added successfully");
            SceneService.showAlertBox(Alert.AlertType.INFORMATION,"Alert Car Rental", "Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been added successfully.", "Procedure complete");
            SceneService.switchToScene("vehiclesMain-view.fxml", event);
        }
    }
}
