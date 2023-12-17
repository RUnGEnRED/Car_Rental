package com.project.car_rental.client.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.car_rental.client.db_models.Customer;
import com.project.car_rental.client.server_connection.ServerConnectionHandler;
import com.project.car_rental.client.services.DataProcessor;
import com.project.car_rental.client.services.Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class CustomersMainController {

    @FXML private TableView<Customer> tableView;

    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, String> numberColumn;
    @FXML private TableColumn<Customer, String> localColumn;
    @FXML private TableColumn<Customer, String> cityColumn;
    @FXML private TableColumn<Customer, String> zipCodeColumn;

    @FXML
    public void initialize() throws IOException {
        loadCustomers();
    }

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneCustomersAdd(ActionEvent event) throws IOException {
        Utilities.switchToScene("customersAdd-view.fxml", event);
    }

    @FXML
    public void switchToSceneCustomersEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("customersEdit-view.fxml", event);
    }

    @FXML
    public void loadCustomers() throws IOException {
        DataProcessor dataProcessor = new DataProcessor();
        String serializeObject = dataProcessor.serializeObjectToString(new Object());
        String messageToServer = dataProcessor.connectPartsToString("getCustomerTable", serializeObject);

        ServerConnectionHandler client = new ServerConnectionHandler();
        client.sendMessage(messageToServer);
        String messageFromServer = client.listenForMessage();

        dataProcessor.splitStringToParts(messageFromServer);

        Type listType = new TypeToken<List<Customer>>(){}.getType();
        List<Customer> customers = new Gson().fromJson(dataProcessor.getContent(), listType);

        ObservableList<Customer> data = FXCollections.observableArrayList(customers);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        localColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        tableView.setItems(data);
    }
}
