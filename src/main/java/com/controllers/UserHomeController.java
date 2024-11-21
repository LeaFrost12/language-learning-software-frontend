package com.controllers;

import java.io.IOException;

import com.language.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.model.*;

public class UserHomeController implements Initializable {
    private LanguageSystemFacade facade;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = LanguageSystemFacade.getInstance();
        user = facade.getCurrentUser();
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
