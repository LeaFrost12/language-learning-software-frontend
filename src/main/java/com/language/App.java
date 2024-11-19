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
            // Load the login page as the initial scene
            scene = new Scene(loadFXML("login"), 640, 480);
            
            scene.getStylesheets().add(App.class.getResource("/com/language/styles.css").toExternalForm());
            
            stage.setScene(scene);
            stage.setTitle("Language Learning App - Login");
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
