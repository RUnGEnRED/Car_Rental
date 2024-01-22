package com.project.car_rental.client.controllers;

import com.project.car_rental.client.services.SceneService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * This class is the controller for the main car rental view.
 */
public class CarRentalMainController {

    /**
     * Switches to the book vehicle main scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneBookVehicleMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("bookVehicleMain-view.fxml", event);
    }

    /**
     * Switches to the reservations main scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("reservationsMain-view.fxml", event);
    }

    /**
     * Switches to the customers main scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneCustomersMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("customersMain-view.fxml", event);
    }

    /**
     * Switches to the vehicles main scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneVehiclesMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("vehiclesMain-view.fxml", event);
    }
}
