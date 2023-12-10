package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;

import java.io.IOException;

public class BookCarMainController {

    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }
}
