package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class VehiclesMainController {

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneVehiclesAdd(ActionEvent event) throws IOException {
        Utilities.switchToScene("vehiclesAdd-view.fxml", event);
    }

    @FXML
    public void switchToSceneVehiclesEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("vehiclesEdit-view.fxml", event);
    }
}
