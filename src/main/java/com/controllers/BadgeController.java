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

    // Flag to determine if the badge has already been added
    private boolean badgeAdded = false;

    public void initialize() {
        // Dynamically add badges to the container
        if (!badgeAdded) {
            addBadgeImage();
            badgeAdded = true;
        }
    }

    private void addBadgeImage() {
        // Create an ImageView for the badge
        ImageView badgeImageView = new ImageView();
        badgeImageView.setImage(new Image(App.class.getResourceAsStream("/com/language/images/badge1.png")));
        badgeImageView.setFitWidth(100); // Set the image width
        badgeImageView.setFitHeight(100); // Set the image height
        badgeImageView.setPreserveRatio(true);

        // Create a description for the badge
        Text badgeDescription = new Text("Congratulations! You earned the Lesson 1 badge!");
        badgeDescription.setStyle("-fx-font-size: 14; -fx-padding: 5;");

        // Add the badge image and description to the container
        badgeContainer.getChildren().addAll(badgeImageView, badgeDescription);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
