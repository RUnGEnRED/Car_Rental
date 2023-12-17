package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class BookVehicleMainController {

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }
}
