package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SettingsController implements Initializable {
    private LanguageSystemFacade facade;
    private User currentUser;

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Retrieve the current user from facade
        facade = LanguageSystemFacade.getInstance();
        currentUser = facade.getCurrentUser();
    }

    @FXML
    private void onChangePassClicked(ActionEvent event) throws IOException {
        App.setRoot("changePass");
    }

    @FXML
    private void onAccountDetailsClicked(ActionEvent event) throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) throws IOException {
        if (facade.logout()) {
            App.setRoot("home");
        } else {
            label.setText("Error logging out. Please try again.");
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
