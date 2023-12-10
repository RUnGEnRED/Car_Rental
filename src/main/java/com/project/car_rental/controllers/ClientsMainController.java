package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ClientsMainController {

    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    public void switchToSceneClientsAdd(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsAdd-view.fxml", event);
    }

    public void switchToSceneClientsEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsEdit-view.fxml", event);
    }
}
