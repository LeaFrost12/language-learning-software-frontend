package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    private LanguageSystemFacade facade;

    public LoginController() {
        // Initialize the UserList singleton
        this.facade = LanguageSystemFacade.getInstance();
    }
    @FXML
    private void btnLoginClicked(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        User currentUser = facade.login(username, password);

        if (currentUser != null) {
            App.setRoot("user_home");
        } else {
            lbl_error.setText("Invalid username or password. Please try again.");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

}