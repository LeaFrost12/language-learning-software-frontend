package com.controllers;

import java.io.IOException;
import com.language.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * The Welcome page 
 * Options to sign up, login, and navigate to other features
 */
public class HomeController {

    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        App.setRoot("signup");
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
        App.setRoot("profile");
    }

    @FXML
    private void onStartLessonClicked(ActionEvent event) throws IOException {
        // Code to handle starting a lesson
        App.setRoot("lesson");
    }
}
