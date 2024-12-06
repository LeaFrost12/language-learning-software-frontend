package com.controllers;

import java.io.IOException;

import com.model.Lesson;
import com.model.User;
import com.model.UserList;
import com.model.Unit;
import com.language.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProgressController {
    @FXML
    private Label lessonTitleLabel;

    @FXML
    private Label unitTitleLabel;

    private User currentUser;
    private Lesson currentLesson;
    private Unit currentUnit;
    
    /**
     * Initialize progress
     * Access current user's lesson and unit.
     */
    public void initialize() {
        //Get current user
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();

        //Check if user is null
        if (currentUser != null) {
            currentLesson = currentUser.getCurrentLesson();
            currentUnit = currentUser.getCurrentUnit();

            //Set lesson label
            if (currentLesson != null) {
                lessonTitleLabel.setText(currentLesson.getLessonName());

            } else {
                lessonTitleLabel.setText("No current lesson found.");
            }

            //Set unit label
            if (currentUnit != null) {
                unitTitleLabel.setText(currentUnit.getUnitName());

            } else {
                unitTitleLabel.setText("No current unit found.");
            }

        } else {
            lessonTitleLabel.setText("User not logged in.");
        }
    }

    @FXML
    private void onNextLessonClicked(ActionEvent event) throws IOException {
        App.setRoot("lessons");
    }

    @FXML
    private void onFlashcardsClicked(ActionEvent event) throws IOException {
        App.setRoot("flashcard");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
