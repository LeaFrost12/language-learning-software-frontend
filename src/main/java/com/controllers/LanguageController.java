package com.controllers;

import com.language.App;
import com.model.LanguageSystemFacade;
import com.model.LanguagesEnum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LanguageController {

    @FXML
    private VBox languageButtonsContainer;

    private final LanguageSystemFacade facade = LanguageSystemFacade.getInstance();

    public void initialize() {
        // Dynamically create buttons for each language in LanguagesEnum
        for (LanguagesEnum language : LanguagesEnum.values()) {
            Button languageButton = new Button(language.name());
            languageButton.setStyle("-fx-font-size: 14pt; -fx-padding: 10px;");
            languageButton.setOnAction(e -> selectLanguage(language));
            languageButtonsContainer.getChildren().add(languageButton);
        }
    }

    private void selectLanguage(LanguagesEnum language) {
        boolean success = facade.loadLanguage(language.name());
        if (success) {
            System.out.println("Language selected: " + language.name());
            // Navigate to user home or next appropriate page
            App.setRoot("user_home");
        } else {
            System.out.println("Failed to load language: " + language.name());
        }
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        // Navigate back to the User Home page
        App.setRoot("user_home");
    }
}

