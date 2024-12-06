package com.controllers;

import com.language.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class StoryController {

    @FXML
    private Label storyLabel;

    public void initialize() {
        // Set a beginner-level story paragraph featuring specified words
        String storyContent = "Hola, me llamo Juan. Vivo en una casa verde muy bonita. Tengo un gato que le gusta saltar y caminar rápido. Cada mañana, el niño dice 'adios' y va a la escuela.";
        storyLabel.setText(storyContent);
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}

