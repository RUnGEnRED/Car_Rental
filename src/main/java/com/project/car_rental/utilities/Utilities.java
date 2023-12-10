package com.project.car_rental.utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utilities {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static final String FXML_BASE_PATH = "/com/project/car_rental/fxml/";

    @FXML
    public static void switchToScene(String sceneName, ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Utilities.class.getResource(FXML_BASE_PATH + sceneName)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static String getFxmlBasePath() {
        return FXML_BASE_PATH;
    }
}
