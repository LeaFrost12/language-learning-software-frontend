package com.language;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            // Load the initial login page
            scene = new Scene(loadFXML("user_home"), 640, 480);

            // Add the stylesheet to the scene
            scene.getStylesheets().add(App.class.getResource("/com/language/styles.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            stage.setTitle("Language Learning App");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading the initial FXML file: " + e.getMessage());
        }
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml the name of the FXML file to load (without the .fxml extension)
     */
    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error switching to FXML file '" + fxml + "': " + e.getMessage());
        }
    }

    /**
     * Loads an FXML file and returns it as a Parent object.
     *
     * @param fxml the name of the FXML file to load (without the .fxml extension)
     * @return the loaded Parent object
     * @throws IOException if the FXML file cannot be loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        if (fxmlLoader.getLocation() == null) {
            throw new IOException("FXML file not found: " + fxml);
        }
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
