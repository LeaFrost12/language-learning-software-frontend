package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.language.App;
import java.io.IOException;

public class BadgeController {

    @FXML
    private VBox badgeContainer;

    @FXML
    private Button backButton;

    // Flags to prevent duplicate badge additions
    private boolean badge1Added = false;
    private boolean badge2Added = false;

    public void initialize() {
        // Dynamically add badges to the container
        if (!badge1Added) {
            addBadgeImage("/com/language/images/badge1.png", "Congratulations! You earned the Unit 1 Lesson 1 badge!");
            badge1Added = true;
        }
        if (!badge2Added) {
            addBadgeImage("/com/language/images/badge2.png", "Congratulations! You earned the Unit 2 Lesson 1 badge!");
            badge2Added = true;
        }
    }

    private void addBadgeImage(String imagePath, String description) {
        // Create an ImageView for the badge
        ImageView badgeImageView = new ImageView();
        badgeImageView.setImage(new Image(App.class.getResourceAsStream(imagePath)));
        badgeImageView.setFitWidth(100); // Set the image width
        badgeImageView.setFitHeight(100); // Set the image height
        badgeImageView.setPreserveRatio(true);

        // Create a description for the badge
        Text badgeDescription = new Text(description);
        badgeDescription.setStyle("-fx-font-size: 14; -fx-padding: 5;");

        // Add the badge image and description to the container
        badgeContainer.getChildren().addAll(badgeImageView, badgeDescription);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}