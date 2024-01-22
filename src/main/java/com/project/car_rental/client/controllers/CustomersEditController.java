package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Customer;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.ConnectionService;
import com.project.car_rental.client.services.CustomersService;
import com.project.car_rental.client.services.SceneService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the Edit Customers view in the Car Rental application.
 */
public class CustomersEditController {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField address;
    @FXML private TextField number;
    @FXML private TextField local;
    @FXML private TextField city;
    @FXML private TextField zipCode;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        loadCustomer();
    }

    /**
     * Switches to the main Customers scene.
     *
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void switchToSceneCustomersMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("customersMain-view.fxml", event);
    }

    /**
     * Loads the selected customer's data into the text fields.
     */
    private void loadCustomer() {
        Customer customer = CustomersMainController.getSelectedCustomer();

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        phone.setText(customer.getPhone());
        email.setText(customer.getEmail());
        address.setText(customer.getAddress());
        number.setText(customer.getNumber());
        local.setText(customer.getLocal());
        city.setText(customer.getCity());
        zipCode.setText(customer.getZipCode());

        System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been loaded successfully");
    }

    /**
     * Updates the selected customer's data based on the input from the text fields.
     *
     * @param event The event that triggered the customer update.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void updateCustomer(ActionEvent event) throws IOException {
        if(!new CustomersService().validateCustomerData(firstName.getText(), lastName.getText(), phone.getText(),
                email.getText(), address.getText(), number.getText(),
                local.getText(), city.getText(), zipCode.getText()))
        {
            return;
        }

        Customer customer = CustomersMainController.getSelectedCustomer();
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setPhone(phone.getText());
        customer.setEmail(email.getText());
        customer.setAddress(address.getText());
        customer.setNumber(number.getText());
        customer.setLocal(local.getText());
        customer.setCity(city.getText());
        customer.setZipCode(zipCode.getText());

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("updateCustomer", customer);

        if(dataProcessor.getCommand().equals("customerUpdated")) {
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been updated successfully");
            SceneService.showAlertBox(AlertType.INFORMATION,"Alert Car Rental", "Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been updated successfully.", "Procedure complete");
            SceneService.switchToScene("customersMain-view.fxml", event);
        }
    }

    /**
     * Deletes the selected customer.
     *
     * @param event The event that triggered the customer deletion.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void deleteCustomer(ActionEvent event) throws IOException {
        Customer customer = CustomersMainController.getSelectedCustomer();

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("deleteCustomer", customer);

        if(dataProcessor.getCommand().equals("customerDeleted")){
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been deleted successfully");
            SceneService.showAlertBox(AlertType.INFORMATION,"Alert Car Rental", "Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been deleted successfully.", "Procedure complete");
            SceneService.switchToScene("customersMain-view.fxml", event);
        }
    }
}
