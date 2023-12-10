package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CarFleetMainController {

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneCarFleetAdd(ActionEvent event) throws IOException {
        Utilities.switchToScene("carFleetAdd-view.fxml", event);
    }

    @FXML
    public void switchToSceneCarFleetEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("carFleetEdit-view.fxml", event);
    }
}
