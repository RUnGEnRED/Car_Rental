package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CarRentalMainController {

    @FXML
    public void switchToSceneBookVehicleMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("bookVehicleMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("reservationsMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneCustomersMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("customersMain-view.fxml", event);
    }
    @FXML
    public void switchToSceneVehiclesMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("vehiclesMain-view.fxml", event);
    }
}
