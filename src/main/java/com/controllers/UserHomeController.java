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

public class UserHomeController implements Initializable {
    private LanguageSystemFacade facade;
    private User user;

    @FXML
    private Label welcomeLabel; // Reference to the welcome label in FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void onHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void onLessonsClicked(ActionEvent event) throws IOException {
        App.setRoot("lessons");
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
    private void onStartLessonClicked(ActionEvent event) throws IOException {
        // Code to handle starting a lesson
        App.setRoot("lesson");
    }
}

