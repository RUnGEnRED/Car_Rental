package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Reservation;
import com.project.car_rental.client.db.models.Vehicle;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.ConnectionService;
import com.project.car_rental.client.services.DataService;
import com.project.car_rental.client.services.ReservationService;
import com.project.car_rental.client.services.SceneService;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;

/**
 * This class is the controller for the main reservation view.
 */
public class ReservationsMainController {

    private static Reservation selectedReservation;

    @FXML private TextField reservationSearchBar;

    @FXML private TableView<Reservation> reservationTableView;

    @FXML private TableColumn<Reservation, Integer> reservationIdColumn;
    @FXML private TableColumn<Reservation, String> customerNameColumn;
    @FXML private TableColumn<Reservation, String> vehicleNameColumn;
    @FXML private TableColumn<Reservation, Date> rentalDateColumn;
    @FXML private TableColumn<Reservation, Date> returnDateColumn;
    @FXML private TableColumn<Reservation, Double> totalCostColumn;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        selectedReservation = null;

        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        rentalDateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        readReservationList();
    }

    /**
     * Switches to the main car rental scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("carRentalMain-view.fxml", event);
    }

    /**
     * Switches to the reservation edit scene.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void switchToSceneReservationsEdit(ActionEvent event) throws IOException {
        selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            System.err.println("No reservation selected");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "", "No reservation selected.");
        } else {
            System.out.println("Selected reservation: " + selectedReservation.getReservationID() + " " + selectedReservation.getCustomerID());
            SceneService.switchToScene("reservationsEdit-view.fxml", event);
        }
    }

    /**
     * Reads the reservation list from the server.
     */
    private void readReservationList() {
        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("readReservationList", new Object());
        ObservableList<Reservation> observableList = DataService.getObservableList(dataProcessor.getContent(), Reservation.class);
        reservationTableView.setItems(observableList);

        SortedList<Reservation> sortedList = new ReservationService().searchReservation(reservationSearchBar, observableList);
        sortedList.comparatorProperty().bind(reservationTableView.comparatorProperty());
        reservationTableView.setItems(sortedList);

        if (dataProcessor.getCommand().equals("reservationList"))
            System.out.println("Reservations has been loaded successfully");
    }

    /**
     * Deletes a reservation.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void deleteReservation(ActionEvent event) throws IOException {
        selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            System.err.println("No reservation selected");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "", "No reservation selected.");
            return;
        }

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("deleteReservation", selectedReservation);

        if(dataProcessor.getCommand().equals("reservationDeleted")){
            System.out.println("Reservation " + selectedReservation.getReservationID() + " " + selectedReservation.getCustomerName() + " " + selectedReservation.getVehicleName() + " has been deleted successfully");
            SceneService.showAlertBox(Alert.AlertType.INFORMATION,"Alert Car Rental", "Reservation " + selectedReservation.getReservationID() + " " + selectedReservation.getCustomerName() + " " + selectedReservation.getVehicleName() + " has been deleted successfully.", "Procedure complete");
            readReservationList();
        }
    }

    /**
     * Gets the selected reservation.
     *
     * @return The selected reservation.
     */
    public static Reservation getSelectedReservation() {
        return selectedReservation;
    }
}
