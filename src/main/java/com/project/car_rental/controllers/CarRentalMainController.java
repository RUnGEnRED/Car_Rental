package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CarRentalMainController {

    @FXML
    public void switchToSceneBookCarMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("bookCarMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("reservationsMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneClientsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsMain-view.fxml", event);
    }
    @FXML
    public void switchToSceneCarFleetMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carFleetMain-view.fxml", event);
    }
}
