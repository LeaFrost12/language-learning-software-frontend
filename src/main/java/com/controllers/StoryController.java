package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.language.App;
import com.narration.Narrator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StoryController {

    @FXML
    private Label storyTitleLabel;

    @FXML
    private TextFlow storyTextFlow; // Use TextFlow to support clickable words

    @FXML
    private Button unit2StoryButton; // Reference to Unit 2 Story button

    @FXML
    private ImageView buzzWordImageView; // Reference to ImageView for buzz word images

    // Map to store buzz words and their image paths
    private Map<String, String> buzzWordImageMap;

    public void initialize() {
        // Initialize the buzz word image map
        buzzWordImageMap = new HashMap<>();
        buzzWordImageMap.put("verde", "/com/language/images/verde.png");
        buzzWordImageMap.put("casa", "/com/language/images/casa.png");
        buzzWordImageMap.put("caminar", "/com/language/images/caminar.png");
        buzzWordImageMap.put("saltar", "/com/language/images/saltar.png");
        buzzWordImageMap.put("gato", "/com/language/images/gato.png");
        buzzWordImageMap.put("rapido", "/com/language/images/rapido.png");
        buzzWordImageMap.put("escuela", "/com/language/images/escuela.png");

        // Set the initial story content
        String storyContent = "Hola, me llamo Juan. Vivo en una casa verde muy bonita. Tengo un gato que le gusta saltar y caminar rápido. Cada mañana, el niño dice 'adios' y va a la escuela.";
        setBuzzWordListeners(storyContent);
    }

    @FXML
    private void onNarrateStoryClicked(ActionEvent event) {
        // Narrate the story using the Narrator class
        String storyContent = getStoryContentFromTextFlow();
        Narrator.playSound(storyContent);
    }

    @FXML
    private void onUnit2StoryClicked(ActionEvent event) {
        // Set the Unit 2 story content
        String storyContent = "Hola, soy Ana. Vivo en una casa grande y bonita. En mi casa, hay un jardín con muchas flores de colores. Mi gato, que se llama Max, siempre camina rápido por el jardín. Cada mañana, saludo a mis amigos y nos vamos a la escuela juntos. Después de la escuela, me gusta saltar la cuerda y jugar con mi perro, que también es muy rápido. ¡Me encanta mi casa y mis amigos!";
        
        storyTitleLabel.setText("Unit 2 Story");
        setBuzzWordListeners(storyContent);

        // Hide the Unit 2 Story button when already on the Unit 2 story
        unit2StoryButton.setVisible(false);
        unit2StoryButton.setManaged(false);
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }

    // Method to set listeners for buzz words
    private void setBuzzWordListeners(String storyContent) {
        // Clear the existing content in TextFlow
        storyTextFlow.getChildren().clear();

        // Split the story content into individual words
        String[] words = storyContent.split(" ");

        // Add listeners to buzz words and format the text
        for (String word : words) {
            String cleanedWord = word.replaceAll("[.,!?]", "").toLowerCase(); // Clean punctuation

            Text textNode;
            if (buzzWordImageMap.containsKey(cleanedWord)) {
                textNode = new Text(word + " ");
                textNode.setStyle("-fx-fill: blue; -fx-underline: true;"); // Make buzz words visually distinct
                textNode.setOnMouseClicked(event -> showBuzzWordImage(cleanedWord));
            } else {
                textNode = new Text(word + " ");
            }

            storyTextFlow.getChildren().add(textNode);
        }
    }

    // Method to show the buzz word image
    private void showBuzzWordImage(String buzzWord) {
        String imagePath = buzzWordImageMap.get(buzzWord);
        if (imagePath != null) {
            Image buzzWordImage = new Image(App.class.getResourceAsStream(imagePath));
            buzzWordImageView.setImage(buzzWordImage);
        }
    }

    // Method to extract the story content from TextFlow (for narration)
    private String getStoryContentFromTextFlow() {
        StringBuilder content = new StringBuilder();
        storyTextFlow.getChildren().forEach(node -> {
            if (node instanceof Text) {
                content.append(((Text) node).getText());
            }
        });
        return content.toString();
    }
}