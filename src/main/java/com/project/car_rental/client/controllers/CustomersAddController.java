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

import org.apache.log4j.Logger;

/**
 * Controller class for the Add Customers view in the Car Rental application.
 */
public class CustomersAddController {

    private static final Logger logger = Logger.getLogger(CustomersAddController.class);

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
     * Creates a new customer and sends it to the server.
     *
     * @param event The event that triggered the customer creation.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void createCustomer(ActionEvent event) throws IOException {
        if(!new CustomersService().validateCustomerData(firstName.getText(), lastName.getText(), phone.getText(),
                email.getText(), address.getText(), number.getText(),
                local.getText(), city.getText(), zipCode.getText()))
        {
            return;
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setPhone(phone.getText());
        customer.setEmail(email.getText());
        customer.setAddress(address.getText());
        customer.setNumber(number.getText());
        customer.setLocal(local.getText());
        customer.setCity(city.getText());
        customer.setZipCode(zipCode.getText());

        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("createCustomer", customer);

        if(dataProcessor.getCommand().equals("customerCreated")) {
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been added successfully");
            SceneService.showAlertBox(AlertType.INFORMATION,"Alert Car Rental", "Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been added successfully.", "Procedure complete");
            SceneService.switchToScene("customersMain-view.fxml", event);
        }
    }
}
