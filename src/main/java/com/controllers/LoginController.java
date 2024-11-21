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
public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    private UserList userList;

    public LoginController() {
        // Initialize the UserList singleton
        this.userList = UserList.getInstance();
    }
    @FXML
    private void btnLoginClicked(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        if (userList.validPass(username, password)) {
            // Set the current user
            User loggedInUser = userList.getUser(username);
            userList.setCurrentUser(loggedInUser);

            // Navigate to the user home page
            App.setRoot("user_home");
        } else {
            lbl_error.setText("Invalid username or password. Please try again.");
        }
    }
}