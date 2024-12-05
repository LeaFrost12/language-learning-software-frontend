package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.model.Badge;
import com.model.BadgeList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import com.language.App;
import java.io.IOException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
