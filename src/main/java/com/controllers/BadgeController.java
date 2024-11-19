package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.model.Badge;
import com.model.BadgeList;

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
}
