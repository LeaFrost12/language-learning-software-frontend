package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import com.language.App;

public class HomeController {

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private ImageView whaleImage;

    public void initialize() {
        // Load the whale image
        Image whale = new Image(App.class.getResourceAsStream("/com/language/images/whale.png"));
        whaleImage.setImage(whale);
    }

    @FXML
    private void onLoginClicked(ActionEvent event) throws Exception {
        App.setRoot("login");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws Exception {
        App.setRoot("signup");
    }
}
