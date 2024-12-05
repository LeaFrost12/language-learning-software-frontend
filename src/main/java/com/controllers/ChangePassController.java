package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.User;
import com.model.UserList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for the Login page
 */
public class ChangePassController {


    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_oldPassword;
    @FXML
    private TextField txt_newPassword;
    @FXML
    private TextField txt_confirmPassword;
    @FXML
    private Label lbl_error;

    private UserList userList;

    public ChangePassController() {
        // Initialize the UserList singleton
        this.userList = UserList.getInstance();
    }
    @FXML
    private void btnChangeClicked(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String oldPassword = txt_oldPassword.getText();
        String newPassword = txt_newPassword.getText();
        String confirmNewPassword = txt_confirmPassword.getText();


        if (newPassword.equals(confirmNewPassword) && userList.validPass(username, oldPassword)) { //Checks that the new passwords are the same and the old password matches the username
            // Set the current user
            User loggedInUser = userList.getUser(username);

            loggedInUser.changePassword(newPassword, oldPassword);

            userList.setCurrentUser(loggedInUser);

            // Navigate to the user home page
            App.setRoot("settings");
        } else {
            lbl_error.setText("Passwords did not match. Please try again.");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

}
