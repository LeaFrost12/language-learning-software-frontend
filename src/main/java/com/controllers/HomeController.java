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

}
