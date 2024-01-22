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

/**
 * Service class for handling scene-related operations.
 */
public class SceneService {

    private static String FXML_BASE_PATH = null;

    /**
     * Switches to a specified scene.
     *
     * @param sceneName The name of the scene to switch to.
     * @param event The event that triggered the scene switch.
     * @throws IOException If there is an error loading the FXML file.
     */
    public static void switchToScene(String sceneName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneService.class.getResource(FXML_BASE_PATH + sceneName)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows an alert box with a specified type, title, message, and header.
     *
     * @param type The type of the alert box.
     * @param title The title of the alert box.
     * @param message The message of the alert box.
     * @param header The header of the alert box.
     */
    public static void showAlertBox(AlertType type, String title, String message, String header) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Gets the base path of the FXML files.
     *
     * @return The base path of the FXML files.
     */
    public static String getFxmlBasePath() {
        return FXML_BASE_PATH;
    }

    /**
     * Sets the base path of the FXML files.
     *
     * @param fxmlBasePath The base path to set.
     */
    public static void setFxmlBasePath(String fxmlBasePath) {
        FXML_BASE_PATH = fxmlBasePath;
    }
}
