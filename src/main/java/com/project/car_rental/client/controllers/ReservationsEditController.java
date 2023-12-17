package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ReservationsEditController {

    @FXML
    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("reservationsMain-view.fxml", event);
    }
}
