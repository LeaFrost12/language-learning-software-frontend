package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.Language;
import com.model.LanguageSystemFacade;
import com.model.LanguagesEnum;
import com.model.TempUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LanguageController {

    @FXML
    private VBox languageButtonsContainer;

    private final LanguageSystemFacade facade = LanguageSystemFacade.getInstance();

    public void initialize() {
        // Dynamically create buttons for each language in LanguagesEnum
        for (LanguagesEnum languageEnum : LanguagesEnum.values()) {
            Button languageButton = new Button(languageEnum.name());
            languageButton.setStyle("-fx-font-size: 14pt; -fx-padding: 10px;");
            languageButton.setOnAction(e -> handleLanguageSelection(languageEnum));
            languageButtonsContainer.getChildren().add(languageButton);
        }
    }

    private void handleLanguageSelection(LanguagesEnum languageEnum) {
        // Attempt to load the selected language
        boolean isLoaded = facade.loadLanguageTempUser(languageEnum.name());
        if (!isLoaded) {
            System.out.println("Failed to load language: " + languageEnum.name());
            return;
        }
    
        // Retrieve temporary user information
        TempUser tempUser = facade.getTempUser();
        if (tempUser == null) {
            System.out.println("Temporary user information is missing.");
            return;
        }
    
        // Retrieve the loaded language from the current user
        Language selectedLanguage = facade.getCurrentUser().getLanguage();
        if (selectedLanguage == null) {
            System.out.println("Language was loaded but could not be retrieved.");
            return;
        }
    
        // Finalize user registration
        boolean success = facade.createUser(
                tempUser.getFirstName(),
                tempUser.getLastName(),
                tempUser.getEmail(),
                tempUser.getPhoneNumber(),
                tempUser.getUsername(),
                tempUser.getPassword(),
                selectedLanguage
        );
    
        if (success) {
            System.out.println("User registered successfully with language: " + selectedLanguage.getLanguageName());
            facade.login(tempUser.getUsername(), tempUser.getPassword());
            App.setRoot("user_home");
        } else {
            System.out.println("Failed to finalize user registration.");
        }
    }
    

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
