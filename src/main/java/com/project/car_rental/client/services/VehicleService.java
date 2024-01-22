package com.project.car_rental.client.services;

import com.project.car_rental.client.db.models.Vehicle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service class for handling vehicle-related operations.
 */
public class VehicleService {

    /**
     * Searches for vehicles based on the input from a search bar.
     *
     * @param searchBar The TextField object representing the search bar.
     * @param vehicleList The list of vehicles to search through.
     * @return A SortedList of vehicles that match the search criteria.
     */
    public SortedList<Vehicle> searchVehicles(TextField searchBar, ObservableList<Vehicle> vehicleList) {
        FilteredList<Vehicle> filteredData = new FilteredList<>(vehicleList, b -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vehicle -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (vehicle.getBrand().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (vehicle.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (vehicle.getVehicleClass().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(vehicle.getYearOfProduction()).contains(lowerCaseFilter)) {
                    return true;
                }
                // CAN BE ADDED MORE

                return false;
            });
        });

        return new SortedList<>(filteredData);
    }

    /**
     * Validates the data of a vehicle.
     *
     * @param brandText The brand of the vehicle.
     * @param modelText The model of the vehicle.
     * @param vehicleClassText The class of the vehicle.
     * @param yearOfProductionText The year of production of the vehicle.
     * @param transmissionText The transmission of the vehicle.
     * @param horsepowerText The horsepower of the vehicle.
     * @param fuelConsumptionText The fuel consumption of the vehicle.
     * @param fuelTypeText The fuel type of the vehicle.
     * @param pricePerDayText The price per day of the vehicle.
     * @return true if the data is valid, false otherwise.
     */
    public boolean validateVehiclesData(String brandText, String modelText, String vehicleClassText,
                                        String yearOfProductionText, String transmissionText, String horsepowerText,
                                        String fuelConsumptionText, String fuelTypeText, String pricePerDayText)
    {
        Pattern pattern = null;
        Matcher matcher = null;

        // Check if the fields are not empty
        if (brandText.isEmpty() || modelText.isEmpty() || vehicleClassText.isEmpty() || transmissionText.isEmpty() ||
                fuelTypeText.isEmpty()) {
            System.err.println("All necessary fields must be completed");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "All necessary fields must be completed.", "Incorrect data");
            return false;
        }

        // Validate brand format
        String brandRegex = "^[\\p{Lu}][\\p{L} .'-]*$";
        pattern = Pattern.compile(brandRegex);
        matcher = pattern.matcher(brandText);
        if (!matcher.matches()) {
            System.err.println("Invalid brand format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid brand format.", "Incorrect data");
            return false;
        }

        // Validate model format
        matcher = pattern.matcher(modelText);
        if (!matcher.matches()) {
            System.err.println("Invalid model format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid model format.", "Incorrect data");
            return false;
        }

        // Validate vehicle class format
        matcher = pattern.matcher(vehicleClassText);
        if (!matcher.matches()) {
            System.err.println("Invalid vehicle class format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid vehicle class format.", "Incorrect data");
            return false;
        }

        // Validate year of production format
        String yearOfProductionRegex = "^\\d{4}$";
        pattern = Pattern.compile(yearOfProductionRegex);
        matcher = pattern.matcher(yearOfProductionText);
        if (!matcher.matches()) {
            System.err.println("Invalid year of production format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid year of production format.", "Incorrect data");
            return false;
        }

        // Validate transmission format
        String transmissionRegex = "^(Manual|Automatic)$";
        pattern = Pattern.compile(transmissionRegex);
        matcher = pattern.matcher(transmissionText);
        if (!matcher.matches()) {
            System.err.println("Invalid transmission format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid transmission format.", "Incorrect data");
            return false;
        }

        // Validate horsepower format
        String horsepowerRegex = "^\\d+$";
        pattern = Pattern.compile(horsepowerRegex);
        matcher = pattern.matcher(horsepowerText);
        if (!matcher.matches()) {
            System.err.println("Invalid horsepower format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid horsepower format.", "Incorrect data");
            return false;
        }

        // Validate fuel consumption format
        String fuelConsumptionRegex = "^\\d+(\\.\\d+)?$";
        pattern = Pattern.compile(fuelConsumptionRegex);
        matcher = pattern.matcher(fuelConsumptionText);
        if (!matcher.matches()) {
            System.err.println("Invalid fuel consumption format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid fuel consumption format.", "Incorrect data");
            return false;
        }

        // Validate fuel type format
        String fuelTypeRegex = "^(Petrol|Diesel|Electric)$";
        pattern = Pattern.compile(fuelTypeRegex);
        matcher = pattern.matcher(fuelTypeText);
        if (!matcher.matches()) {
            System.err.println("Invalid fuel type format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid fuel type format.", "Incorrect data");
            return false;
        }

        // Validate price per day format
        String pricePerDayRegex = "^\\d+(\\.\\d+)?$";
        pattern = Pattern.compile(pricePerDayRegex);
        matcher = pattern.matcher(pricePerDayText);
        if (!matcher.matches()) {
            System.err.println("Invalid price per day format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid price per day format.", "Incorrect data");
            return false;
        }

        return true;
    }
}
