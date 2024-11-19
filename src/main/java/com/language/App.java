package com.language;

import java.io.IOException;
import java.net.URL;
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
            scene = new Scene(loadFXML("home"), 640, 480);
            stage.setScene(scene);
            stage.setTitle("Language Learning App");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading the initial FXML file: " + e.getMessage());
        }
    }

    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error switching to FXML file '" + fxml + "': " + e.getMessage());
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL resource = App.class.getResource(fxml + ".fxml");
        if (resource == null) {
            throw new IOException("FXML file not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
