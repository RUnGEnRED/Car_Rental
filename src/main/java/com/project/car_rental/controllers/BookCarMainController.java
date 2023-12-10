package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class BookCarMainController {

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void showAlertBoxBookButton() {
        Utilities.showAlertBox(AlertType.ERROR,"Alert Box", "There is a message", "There is a header");
    }
}
