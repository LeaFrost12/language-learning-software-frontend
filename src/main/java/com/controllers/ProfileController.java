package com.controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import com.language.App;
import com.model.User;
import com.model.UserList;

public class ProfileController {

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private void onBackToHomeClicked(ActionEvent event) throws IOException {
        // Navigate back to the user home page
        App.setRoot("user_home");
    }

    public void initialize() {
        // Retrieve the current user from UserList
        UserList userList = UserList.getInstance();
        User currentUser = userList.getCurrentUser();

        // Populate the labels with user information
        if (currentUser != null) {
            firstNameLabel.setText("First Name: " + currentUser.getFirstName());
            lastNameLabel.setText("Last Name: " + currentUser.getLastName());
            usernameLabel.setText("Username: " + currentUser.getUsername());
            emailLabel.setText("Email: " + currentUser.getEmail());
            phoneLabel.setText("Phone Number: " + currentUser.getPhoneNumber());
        }
    }
}
