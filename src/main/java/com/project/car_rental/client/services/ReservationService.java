package com.project.car_rental.client.services;

import com.project.car_rental.client.db.models.Reservation;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

/**
 * This class provides services related to reservations.
 */
public class ReservationService {

    /**
     * This method searches for reservations based on the input provided in the search bar.
     *
     * @param searchBar The TextField object that captures the user's search input.
     * @param reservationList The list of reservations to be searched.
     * @return A SortedList of reservations that match the search criteria.
     */
    public SortedList<Reservation> searchReservation(TextField searchBar, ObservableList<Reservation> reservationList) {
        FilteredList<Reservation> filteredData = new FilteredList<>(reservationList, b -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(reservation.getReservationID()).contains(lowerCaseFilter)) {
                    return true; // Matches reservation ID
                } else if (reservation.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Matches customer name
                } else if (reservation.getVehicleName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Matches vehicle name
                }

                return false; // No match found
            });
        });

        return new SortedList<>(filteredData);
    }
}
