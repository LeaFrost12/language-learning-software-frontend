package com.controllers;

import java.io.IOException;

import com.language.App;
import com.narration.Narrator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class StoryController {

    @FXML
    private Label storyTitleLabel;

    @FXML
    private Label storyTextLabel;

    public void initialize() {
        // Set the story content
        String storyContent = "Hola, me llamo Juan. Vivo en una casa verde muy bonita. Tengo un gato que le gusta saltar y caminar rápido. Cada mañana, el niño dice 'adios' y va a la escuela.";
        
        storyTextLabel.setText(storyContent);
    }

    @FXML
    private void onNarrateStoryClicked(ActionEvent event) {
        // Narrate the story using the Narrator class
        String storyContent = storyTextLabel.getText();
        Narrator.playSound(storyContent);
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}