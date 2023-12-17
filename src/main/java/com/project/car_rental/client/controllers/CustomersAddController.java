package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomersAddController {

    @FXML
    public void switchToSceneCustomersMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("customersMain-view.fxml", event);
    }
}
