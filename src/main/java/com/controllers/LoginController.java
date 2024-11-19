package com.controllers;

import java.io.IOException;
import com.language.App;
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

        // Check if the username and password are valid
        if (userList.validPass(username, password)) {
            // Navigate to the home page upon successful login
            App.setRoot("home");
        } else {
            // Display an error message if the credentials are invalid
            lbl_error.setText("Invalid username or password. Please try again.");
        }
    }
}