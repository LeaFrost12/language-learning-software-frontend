package com.controllers;

import java.io.IOException;
import com.language.App;
import com.model.User;
import com.model.UserList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

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
    private void btnChangePassClicked(ActionEvent event) throws IOException {
        String oldPassword = txt_oldPassword.getText();
        String newPassword = txt_newPassword.getText();

        if (userList.validPass(username, password)) {
            // Set the current user
            User loggedInUser = userList.getUser(username);
            userList.setCurrentUser(loggedInUser);

            // Navigate to the user home page
            App.setRoot("user_home");
        } else {
            lbl_error.setText("Passwords did not match. Please try again.");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

}
