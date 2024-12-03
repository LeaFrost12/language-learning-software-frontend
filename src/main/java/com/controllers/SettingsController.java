package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.User;
import com.model.UserList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SettingsController implements Initializable {
    private LanguageSystemFacade facade;
    private UserList userList;
    private User currentUser;

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Retrieve the current user from UserList
        userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();
    }

    @FXML
    private void onChangePassClicked(ActionEvent event) throws IOException {
        App.setRoot("changePass");
    }

    @FXML
    private void onAccountDetailsClicked(ActionEvent event) throws IOException {
        App.setRoot("accountDetails");
    }

    @FXML
    private void onLogoutClicked(ActionEvent event) throws IOException {
        userList.logout(currentUser);
        App.setRoot("home");
    }

}
