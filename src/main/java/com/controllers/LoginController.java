package com.controllers;

import java.io.IOException;

import com.language.App;

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

public class LoginController {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        LanguageSystemFacade facade = LanguageSystemFacade.getInstance();

        if (facade.login(username, password)!=null) {
            lbl_error.setText("Invalid login credentials.");
            return;
        }

        App.setRoot("user_home");
    }
}