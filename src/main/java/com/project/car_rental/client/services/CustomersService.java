package com.project.car_rental.client.services;

import com.project.car_rental.client.db.models.Customer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides services related to customers.
 */
public class CustomersService {

    /**
     * This method searches for customers based on the input provided in the search bar.
     *
     * @param searchBar The TextField object that captures the user's search input.
     * @param customersList The list of customers to be searched.
     * @return A SortedList of customers that match the search criteria.
     */
    public SortedList<Customer> searchCustomers(TextField searchBar, ObservableList<Customer> customersList) {
        FilteredList<Customer> filteredData = new FilteredList<>(customersList, b -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Matches first name
                } else if (customer.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Matches last name
                } else if (customer.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Matches phone number
                }

                return false; // No match found
            });
        });

        return new SortedList<>(filteredData);
    }

    /**
     * This method validates the data of a customer.
     *
     * @param firstNameText The first name of the customer.
     * @param lastNameText The last name of the customer.
     * @param phoneText The phone number of the customer.
     * @param emailText The email address of the customer.
     * @param addressText The address of the customer.
     * @param numberText The number of the customer.
     * @param localText The local of the customer.
     * @param cityText The city of the customer.
     * @param zipCodeText The zip code of the customer.
     * @return A boolean value indicating whether the customer data is valid.
     */
    public boolean validateCustomerData(String firstNameText, String lastNameText, String phoneText, String emailText, String addressText, String numberText, String localText, String cityText, String zipCodeText) {
        Pattern pattern;
        Matcher matcher;

        // Check if the fields are not empty
        if (firstNameText.isEmpty() || lastNameText.isEmpty() || phoneText.isEmpty() || emailText.isEmpty() ||
                addressText.isEmpty() || numberText.isEmpty() || cityText.isEmpty() || zipCodeText.isEmpty()) {
            System.err.println("All necessary fields must be completed");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "All necessary fields must be completed.", "Incorrect data");
            return false;
        }

        // Validate first name format
        String nameRegex = "^[\\p{Lu}][\\p{L} .'-]*$";
        pattern = Pattern.compile(nameRegex);
        matcher = pattern.matcher(firstNameText);
        if (!matcher.matches()) {
            System.err.println("Invalid first name format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid first name format.", "Incorrect data");
            return false;
        }

        // Validate last name format
        matcher = pattern.matcher(lastNameText);
        if (!matcher.matches()) {
            System.err.println("Invalid last name format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid last name format.", "Incorrect data");
            return false;
        }

        // Validate phone number format
        String phoneRegex = "^[+]?[0-9 ()-]{5,15}$";
        pattern = Pattern.compile(phoneRegex);
        matcher = pattern.matcher(phoneText);
        if (!matcher.matches()) {
            System.err.println("Invalid phone number format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid phone number format.", "Incorrect data");
            return false;
        }

        // Validate email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        pattern = Pattern.compile(emailRegex);
        matcher = pattern.matcher(emailText);
        if (!matcher.matches()) {
            System.err.println("Invalid email format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid email format.", "Incorrect data");
            return false;
        }

        // Validate address format
        String addressRegex = "^[\\p{Lu}][\\p{L}0-9 .,-]*$";
        pattern = Pattern.compile(addressRegex);
        matcher = pattern.matcher(addressText);
        if (!matcher.matches()) {
            System.err.println("Invalid address format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid address format.", "Incorrect data");
            return false;
        }

        // Validate number format
        String numberRegex = "^\\d+[a-z]?$";
        pattern = Pattern.compile(numberRegex);
        matcher = pattern.matcher(numberText);
        if (!matcher.matches()) {
            System.err.println("Invalid number format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid number format.", "Incorrect data");
            return false;
        }

        // Validate local format
        String localRegex = "^(0|[1-9]\\d*)?$";
        pattern = Pattern.compile(localRegex);
        matcher = pattern.matcher(localText);
        if (!matcher.matches()) {
            System.err.println("Invalid local format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid local format.", "Incorrect data");
            return false;
        }

        // Validate city format
        String cityRegex = "^[\\p{L} .'-]+$";
        pattern = Pattern.compile(cityRegex);
        matcher = pattern.matcher(cityText);
        if (!matcher.matches()) {
            System.err.println("Invalid city format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid city format.", "Incorrect data");
            return false;
        }

        // Validate zip code format
        String zipCodeRegex = "^\\d{2}-\\d{3}$|^\\d{5}$";
        pattern = Pattern.compile(zipCodeRegex);
        matcher = pattern.matcher(zipCodeText);
        if (!matcher.matches()) {
            System.err.println("Invalid zip code format");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "Invalid zip code format.", "Incorrect data");
            return false;
        }

        return true;
    }
}
