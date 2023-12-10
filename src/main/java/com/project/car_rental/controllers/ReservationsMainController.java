package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ReservationsMainController {

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneReservationsEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("reservationsEdit-view.fxml", event);
    }
}
