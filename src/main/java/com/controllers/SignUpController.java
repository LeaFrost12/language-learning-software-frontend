package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.TempUser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private ImageView whaleImage;

    private final LanguageSystemFacade facade = LanguageSystemFacade.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load the whale image
        Image whale = new Image(App.class.getResourceAsStream("/com/language/images/whale.png"));
        whaleImage.setImage(whale);

    }

    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText().trim();
        String password = txt_password.getText().trim();
        String firstName = txt_first_name.getText().trim();
        String lastName = txt_last_name.getText().trim();
        String phoneNumber = txt_phone_number.getText().trim();
        String email = txt_email.getText().trim();

        

        // Check for empty fields
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            lbl_error.setText("Sorry, you cannot leave blank fields.");
            return;
        }

        // Store user details temporarily until language selection
        TempUser tempUser = new TempUser(firstName, lastName, email, phoneNumber, username, password);

        // Pass the temporary user to the language selection controller
        facade.setTempUser(tempUser);

        // Navigate to language selection page
        App.setRoot("languages");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }
}
