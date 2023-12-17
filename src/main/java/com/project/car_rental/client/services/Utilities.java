package com.project.car_rental.client.services;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utilities {

    private static String FXML_BASE_PATH = null;

    public static void switchToScene(String sceneName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Utilities.class.getResource(FXML_BASE_PATH + sceneName)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void showAlertBox(AlertType type, String title, String message, String header) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String getFxmlBasePath() {
        return FXML_BASE_PATH;
    }

    public static void setFxmlBasePath(String fxmlBasePath) {
        FXML_BASE_PATH = fxmlBasePath;
    }
}
