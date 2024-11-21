package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.language.App;
import com.model.*;
import javafx.scene.layout.AnchorPane;

public class SignUpController implements Initializable{
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_first_name;
    @FXML
    private TextField txt_last_name;
    @FXML
    private TextField txt_phone_number;
    @FXML
    private TextField txt_email;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String firstName = txt_first_name.getText();
        String lastName = txt_last_name.getText();
        String phoneNumber = txt_phone_number.getText();
        String email = txt_email.getText();


        // check for empty fields
        if (username.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")
                || phoneNumber.equals("")) {
            lbl_error.setText("Sorry, You cannot leave blank fields");
            return;
        }

        LanguageSystemFacade facade = LanguageSystemFacade.getInstance();

        if (!facade.createUser(firstName, lastName, email, phoneNumber, username, password, null)) {
            lbl_error.setText("Sorry, this user couldn't be created.");
            return;
        }

        facade.login(username, password);
        User user = facade.getCurrentUser();
        App.setRoot("user_home");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
