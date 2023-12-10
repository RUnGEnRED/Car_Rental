package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CarFleetEditController {

    @FXML
    public void switchToSceneCarFleetMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carFleetMain-view.fxml", event);
    }
}
