package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReservationsEditController {

    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("reservationsMain-view.fxml", event);
    }
}
