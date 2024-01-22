package com.project.car_rental.client;

import com.project.car_rental.client.services.SceneService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class for the Car Rental client application.
 */
public class ClientMain extends Application {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final String FXML_BASE_PATH = "/com/project/car_rental/client/fxml/";
    private static final String ICON_BASE_PATH = "/com/project/car_rental/client/images/icon.png";

    /**
     * Starts the application.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException If there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource(SceneService.getFxmlBasePath() + "carRentalMain-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setTitle("Car Rental");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(ClientMain.class.getResource(ICON_BASE_PATH)).toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for all JavaFX applications.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SceneService.setFxmlBasePath(FXML_BASE_PATH);
        launch();
    }
}
