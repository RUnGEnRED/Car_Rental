package com.project.car_rental.controllers;

import com.project.car_rental.utilities.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ClientsAddController {

    @FXML
    public void switchToSceneClientsMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsMain-view.fxml", event);
    }
}
