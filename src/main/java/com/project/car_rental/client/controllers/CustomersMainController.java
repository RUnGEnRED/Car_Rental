package com.project.car_rental.client.controllers;

import com.project.car_rental.client.db.models.Customer;
import com.project.car_rental.client.server.services.DataProcessor;
import com.project.car_rental.client.services.ConnectionService;
import com.project.car_rental.client.services.CustomersService;
import com.project.car_rental.client.services.DataService;
import com.project.car_rental.client.services.SceneService;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

/**
 * CustomersMainController is a class that handles the actions on the main customers view.
 */
public class CustomersMainController {

    private static Customer selectedCustomer;

    @FXML private TextField customerSearchBar;
    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, String> numberColumn;
    @FXML private TableColumn<Customer, String> localColumn;
    @FXML private TableColumn<Customer, String> cityColumn;
    @FXML private TableColumn<Customer, String> zipCodeColumn;

    /**
     * Initializes the main customers view by reading the customer list.
     */
    @FXML
    private void initialize() {
        selectedCustomer = null;
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        localColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        readCustomerList();
    }

    /**
     * Switches the scene to the main car rental view.
     *
     * @param event The action event that triggered the scene switch.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        SceneService.switchToScene("carRentalMain-view.fxml", event);
    }

    /**
     * Switches the scene to the customers add view.
     *
     * @param event The action event that triggered the scene switch.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void switchToSceneCustomersAdd(ActionEvent event) throws IOException {
        SceneService.switchToScene("customersAdd-view.fxml", event);
    }

    /**
     * Switches the scene to the customers edit view if a customer is selected.
     *
     * @param event The action event that triggered the scene switch.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void switchToSceneCustomersEdit(ActionEvent event) throws IOException {
        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            System.err.println("No customer selected");
            SceneService.showAlertBox(Alert.AlertType.WARNING,"Alert Car Rental", "", "No customer selected.");
        } else {
            System.out.println("Selected customer: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
            SceneService.switchToScene("customersEdit-view.fxml", event);
        }
    }

    /**
     * Reads the customer list from the server and sets it to the customer table view.
     */
    private void readCustomerList() {
        DataProcessor dataProcessor = new ConnectionService().sendDataToServer("readCustomerList", new Object());
        ObservableList<Customer> observableList = DataService.getObservableList(dataProcessor.getContent(), Customer.class);
        customerTableView.setItems(observableList);

        SortedList<Customer> sortedList = new CustomersService().searchCustomers(customerSearchBar, observableList);
        sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());
        customerTableView.setItems(sortedList);

        if(dataProcessor.getCommand().equals("customerList"))
            System.out.println("Customers has been loaded successfully");
    }

    /**
     * Gets the selected customer.
     *
     * @return The selected customer.
     */
    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }
}
