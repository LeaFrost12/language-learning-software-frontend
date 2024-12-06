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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the Login page
 */
public class LoginController implements Initializable{

    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;
    @FXML
    private ImageView whaleImage;

    private LanguageSystemFacade facade = LanguageSystemFacade.getInstance();
    private UserList userList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load the whale image
        Image whale = new Image(App.class.getResourceAsStream("/com/language/images/whale.png"));
        whaleImage.setImage(whale);

        // Initialize the UserList singleton
        this.userList = UserList.getInstance();

    }

    @FXML
    private void btnLoginClicked(ActionEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        if (userList.validPass(username, password)) {
            // Set the current user
            facade.login(username, password);

            // Navigate to the user home page
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