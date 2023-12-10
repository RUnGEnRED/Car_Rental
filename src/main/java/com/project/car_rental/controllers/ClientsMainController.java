package com.project.car_rental.controllers;

import com.project.car_rental.entities.CustomerEntity;
import com.project.car_rental.utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class ClientsMainController {

    @FXML private TableView<CustomerEntity> tableView;

    @FXML private TableColumn<CustomerEntity, String> firstNameColumn;
    @FXML private TableColumn<CustomerEntity, String> lastNameColumn;
    @FXML private TableColumn<CustomerEntity, String> phoneColumn;
    @FXML private TableColumn<CustomerEntity, String> emailColumn;
    @FXML private TableColumn<CustomerEntity, String> addressColumn;
    @FXML private TableColumn<CustomerEntity, String> numberColumn;
    @FXML private TableColumn<CustomerEntity, String> localColumn;
    @FXML private TableColumn<CustomerEntity, String> cityColumn;
    @FXML private TableColumn<CustomerEntity, String> zipCodeColumn;

    @FXML
    public void initialize() {
        loadCustomers();
    }

    @FXML
    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        Utilities.switchToScene("carRentalMain-view.fxml", event);
    }

    @FXML
    public void switchToSceneClientsAdd(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsAdd-view.fxml", event);
    }

    @FXML
    public void switchToSceneClientsEdit(ActionEvent event) throws IOException {
        Utilities.switchToScene("clientsEdit-view.fxml", event);
    }

    @FXML
    public void loadCustomers() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(CustomerEntity.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            List<CustomerEntity> customers = session.createQuery("from CustomerEntity", CustomerEntity.class).list();

            ObservableList<CustomerEntity> data = FXCollections.observableArrayList(customers);

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
}
