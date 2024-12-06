package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.User;
import com.model.UserList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserHomeController implements Initializable {
    private LanguageSystemFacade facade;
    private User user;

    @FXML
    private Label welcomeLabel; // Reference to the welcome label in FXML

    @FXML
    private ImageView whaleImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load the whale image
        Image whale = new Image(App.class.getResourceAsStream("/com/language/images/whale.png"));
        whaleImage.setImage(whale);
        // Retrieve the current user from UserList
        UserList userList = UserList.getInstance();
        User currentUser = userList.getCurrentUser();

        // Set the welcome message
        if (currentUser != null) {
            welcomeLabel.setText("Welcome back, " + currentUser.getFirstName() + "!");
        } else {
            welcomeLabel.setText("Welcome back!");
        }
    }

    @FXML
    private void onContinueUnitClicked(ActionEvent event) throws IOException {
        App.setRoot("progress");
    }

    @FXML
    private void onBadgesClicked(ActionEvent event) throws IOException {
        App.setRoot("badges");
    }

    @FXML
    private void onSettingsClicked(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void onProfileClicked(ActionEvent event) throws IOException {
        // Navigate to the profile page
        App.setRoot("profile");
    }

    @FXML
    private void onLanguagesClicked(ActionEvent event) throws IOException {
        // Navigate to the languages page
        App.setRoot("languages");
    }

    @FXML
    private void onProblemWordsClicked(ActionEvent event) throws IOException {
        // Navigate to the problem words page
        App.setRoot("problemWords");
    }
    @FXML
    private void onViewProblemWordsClicked(ActionEvent event) {
        App.setRoot("problemWords");
    }
}

