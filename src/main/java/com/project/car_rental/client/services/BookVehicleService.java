package com.project.car_rental.client.services;

import com.project.car_rental.client.db.models.Reservation;
import com.project.car_rental.client.db.models.Vehicle;
import com.project.car_rental.client.server.services.DataProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import tornadofx.control.DateTimePicker;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * BookVehicleService is a class that provides methods to handle vehicle booking.
 */
public class BookVehicleService {

    /**
     * Reads the list of vehicles available within a specific date range.
     *
     * @param rentalDate The start date of the rental period.
     * @param returnDate The end date of the rental period.
     * @return A DataProcessor object containing the list of available vehicles.
     */
    public DataProcessor readVehicleListOnDateFrame(DateTimePicker rentalDate, DateTimePicker returnDate) {
        Reservation reservation = convertDateTimeFormat(rentalDate, returnDate);

        return new ConnectionService().sendDataToServer("readVehicleListOnDateFrame", reservation);
    }

    /**
     * Converts the rental and return dates from LocalDateTime to Date format.
     *
     * @param rentalDate The start date of the rental period.
     * @param returnDate The end date of the rental period.
     * @return A Reservation object with the converted dates.
     */
    public Reservation convertDateTimeFormat(DateTimePicker rentalDate, DateTimePicker returnDate) {
        LocalDateTime localDateTime;
        localDateTime = rentalDate.getDateTimeValue();
        Date renDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        localDateTime = returnDate.getDateTimeValue();
        Date retDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Reservation reservation = new Reservation();
        reservation.setRentalDate(renDate);
        reservation.setReturnDate(retDate);

        return reservation;
    }

    /**
     * Creates a list of unique vehicle brands from a TableView of Vehicles.
     *
     * @param vehicleTableView The TableView containing the list of Vehicles.
     * @return An ObservableList of unique vehicle brands.
     */
    public ObservableList<String> createBrandList(TableView<Vehicle> vehicleTableView) {
        ObservableList<String> brands = FXCollections.observableArrayList();

        brands.add("None");
        for (Vehicle vehicle : vehicleTableView.getItems()) {
            String brand = vehicle.getBrand();
            if (!brands.contains(brand))
                brands.add(brand);
        }

        return brands;
    }

    /**
     * Creates a list of unique vehicle classes from a TableView of Vehicles.
     *
     * @param vehicleTableView The TableView containing the list of Vehicles.
     * @return An ObservableList of unique vehicle classes.
     */
    public ObservableList<String> createVehicleClassList(TableView<Vehicle> vehicleTableView) {
        ObservableList<String> vehicleClasses = FXCollections.observableArrayList();

        vehicleClasses.add("None");
        for (Vehicle vehicle : vehicleTableView.getItems()) {
            String vehicleClass = vehicle.getVehicleClass();
            if (!vehicleClasses.contains(vehicleClass))
                vehicleClasses.add(vehicleClass);
        }

        return vehicleClasses;
    }

    /**
     * Validates the data in a Reservation object.
     *
     * @param reservation The Reservation object to be validated.
     * @return A boolean indicating whether the data is valid.
     */
    public boolean validateReservationData(Reservation reservation) {
        int customerIdInt = reservation.getCustomerID();
        int vehicleIdInt = reservation.getVehicleID();
        Date rentalDate = reservation.getRentalDate();
        Date returnDate = reservation.getReturnDate();
        double totalCostDouble = reservation.getTotalCost();

        // Check if the fields are not empty
        if (customerIdInt == 0 || vehicleIdInt == 0 || rentalDate == null || returnDate == null || totalCostDouble == 0.0) {
            System.err.println("All necessary fields must be completed");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "All necessary fields must be completed.", "Incorrect data");
            return false;
        }

        // Validate Customer Id format
        if (customerIdInt < 0) {
            System.err.println("Invalid Customer Id format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid Customer Id format.", "Incorrect data");
            return false;
        }

        // Validate Vehicle Id format
        if (vehicleIdInt < 0) {
            System.err.println("Invalid Vehicle Id format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid Vehicle Id format.", "Incorrect data");
            return false;
        }

        // Validate rental and return dates
        if (rentalDate.after(returnDate)) {
            System.err.println("Rental date cannot be after return date");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Rental date cannot be after return date.", "Incorrect data");
            return false;
        }

        return true;
    }
}
