package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CarFleetEditController {

    public void switchToSceneCarFleetMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carFleetMain-view.fxml", event);
    }
}
