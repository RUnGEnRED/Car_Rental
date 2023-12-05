package com.project.car_rental;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void switchToScene(String sceneName, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName + "-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneCarRentalMain(ActionEvent event) throws IOException {
        switchToScene("carRentalMain", event);
    }

    public void switchToSceneBookCarMain(ActionEvent event) throws IOException {
        switchToScene("bookCarMain", event);
    }

    public void switchToSceneReservationsMain(ActionEvent event) throws IOException {
        switchToScene("reservationsMain", event);
    }

    public void switchToSceneReservationsEdit(ActionEvent event) throws IOException {
        switchToScene("reservationsEdit", event);
    }

    public void switchToSceneClientsMain(ActionEvent event) throws IOException {
        switchToScene("clientsMain", event);
    }

    public void switchToSceneClientsAdd(ActionEvent event) throws IOException {
        switchToScene("clientsAdd", event);
    }

    public void switchToSceneClientsEdit(ActionEvent event) throws IOException {
        switchToScene("clientsEdit", event);
    }

    public void switchToSceneCarFleetMain(ActionEvent event) throws IOException {
        switchToScene("carFleetMain", event);
    }

    public void switchToSceneCarFleetAdd(ActionEvent event) throws IOException {
        switchToScene("carFleetAdd", event);
    }

    public void switchToSceneCarFleetEdit(ActionEvent event) throws IOException {
        switchToScene("carFleetEdit", event);
    }
}
