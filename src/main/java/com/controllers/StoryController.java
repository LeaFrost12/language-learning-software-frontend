package com.controllers;

import java.io.IOException;

import com.language.App;
import com.narration.Narrator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class StoryController {

    @FXML
    private Label storyTitleLabel;

    @FXML
    private Label storyTextLabel;

    @FXML
    private Button unit2StoryButton; // Reference to Unit 2 Story button

    public void initialize() {
        // Set the initial story content
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
    private void onUnit2StoryClicked(ActionEvent event) {
        // Set the Unit 2 story content
        String storyContent = "Hola, soy Ana. Vivo en una casa grande y bonita. En mi casa, hay un jardín con muchas flores de colores. Mi gato, que se llama Max, siempre camina rápido por el jardín. Cada mañana, saludo a mis amigos y nos vamos a la escuela juntos. Después de la escuela, me gusta saltar la cuerda y jugar con mi perro, que también es muy rápido. ¡Me encanta mi casa y mis amigos!";
        
        storyTitleLabel.setText("Unit 2 Story");
        storyTextLabel.setText(storyContent);

        // Hide the Unit 2 Story button when already on the Unit 2 story
        unit2StoryButton.setVisible(false);
        unit2StoryButton.setManaged(false);
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
