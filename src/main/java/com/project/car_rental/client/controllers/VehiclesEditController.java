package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class VehiclesEditController {

    @FXML
    public void switchToSceneVehiclesMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("vehiclesMain-view.fxml", event);
    }
}
