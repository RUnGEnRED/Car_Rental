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
 * VehiclesEditController is a class that handles the actions on the vehicle edit view.
 */
public class VehiclesEditController {

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
     * Initializes the vehicle edit view by loading the selected vehicle.
     */
    @FXML
    private void initialize() {
        loadVehicle();
    }

    /**
     * Switches the scene to the main vehicles view.
     *
     * @param event The action event that triggered the scene switch.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneVehiclesMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("vehiclesMain-view.fxml", event);
    }

    /**
     * Loads the selected vehicle into the text fields.
     */
    private void loadVehicle() {
        Vehicle vehicle = VehiclesMainController.getSelectedVehicle();

        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        vehicleClass.setText(vehicle.getVehicleClass());
        yearOfProduction.setText(String.valueOf(vehicle.getYearOfProduction()));
        transmission.setText(vehicle.getTransmission());
        horsepower.setText(String.valueOf(vehicle.getHorsepower()));
        fuelConsumption.setText(String.valueOf(vehicle.getFuelConsumption()));
        fuelType.setText(vehicle.getFuelType());
        pricePerDay.setText(String.valueOf(vehicle.getPricePerDay()));

        System.out.println("Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been loaded successfully");
    }

    /**
     * Updates the selected vehicle with the data from the text fields.
     *
     * @param event The action event that triggered the vehicle update.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void updateVehicle(ActionEvent event) throws IOException {
        if(!new VehicleService().validateVehiclesData(brand.getText(), model.getText(), vehicleClass.getText(),
                yearOfProduction.getText(), transmission.getText(), horsepower.getText(),
                fuelConsumption.getText(), fuelType.getText(), pricePerDay.getText()))
        {
            return;
        }

        Vehicle vehicle = VehiclesMainController.getSelectedVehicle();
        vehicle.setBrand(brand.getText());
        vehicle.setModel(model.getText());
        vehicle.setVehicleClass(vehicleClass.getText());
        vehicle.setYearOfProduction(Integer.parseInt(yearOfProduction.getText()));
        vehicle.setTransmission(transmission.getText());
        vehicle.setHorsepower(Integer.parseInt(horsepower.getText()));
        vehicle.setFuelConsumption(Double.parseDouble(fuelConsumption.getText()));
        vehicle.setFuelType(fuelType.getText());
        vehicle.setPricePerDay(Double.parseDouble(pricePerDay.getText()));

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("updateVehicle", vehicle);

        if(dataProcessor.getCommand().equals("vehicleUpdated")) {
            System.out.println("Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been updated successfully");
            SceneService.showAlertBox(Alert.AlertType.INFORMATION,"Alert Car Rental", "Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been updated successfully.", "Procedure complete");
            SceneService.switchToScene("vehiclesMain-view.fxml", event);
        }
    }

    /**
     * Deletes the selected vehicle.
     *
     * @param event The action event that triggered the vehicle deletion.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void deleteVehicle(ActionEvent event) throws IOException {
        Vehicle vehicle = VehiclesMainController.getSelectedVehicle();

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("deleteVehicle", vehicle);

        if(dataProcessor.getCommand().equals("vehicleDeleted")){
            System.out.println("Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been deleted successfully");
            SceneService.showAlertBox(Alert.AlertType.INFORMATION,"Alert Car Rental", "Vehicle " + vehicle.getBrand() + " " + vehicle.getModel() + " has been deleted successfully.", "Procedure complete");
            SceneService.switchToScene("vehiclesMain-view.fxml", event);
        }
    }
}
