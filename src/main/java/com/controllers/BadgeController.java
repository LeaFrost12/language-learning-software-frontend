package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.model.Badge;
import com.model.BadgeList;
import com.language.App;
import java.io.IOException;


public class BadgeController {

    @FXML
    private VBox badgeContainer;

    public void initialize() {
        // Get the instance of BadgeList and display badges
        BadgeList badgeList = BadgeList.getInstance();

        for (Badge badge : badgeList.getBadges()) {
            // Create a Text node for each badge
            Text badgeText = new Text("Badge: " + badge.getName() + "\nDescription: " + badge.getDescription());
            badgeContainer.getChildren().add(badgeText);
        }

    }

    /**
     * Goes back to the user home page
     */
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
