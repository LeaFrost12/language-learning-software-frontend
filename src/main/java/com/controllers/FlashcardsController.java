package com.controllers;

import java.io.IOException;

import com.language.App;
import com.model.Unit;
import com.model.User;
import com.model.UserList;
import com.model.Word;
import com.model.WordList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FlashcardsController {

    @FXML
    private Button backButton;

    @FXML
    private Button nextCardButton;

    @FXML
    private Label unitLabel;

    @FXML
    private ImageView waveImage;

    @FXML
    private Label wordLabel;

    private WordList wordList;
    private User currentUser;
    private Unit currentUnit;
    private Word currentWord;
    private boolean showTranslation = false;
    

    @FXML
    private void initialize() {
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();
        
        //Check if user is null
        if (currentUser != null) {
            currentUnit = currentUser.getCurrentUnit();
            wordList = currentUnit.getUnitWordList();
        }
        try {
            nextWord();
        } catch (IllegalStateException e) {
            wordLabel.setText("No words available!");
            nextCardButton.setDisable(true); 
        }
        waveImage.setImage(new Image(getClass().getResourceAsStream("/com/language/images/wave.png")));
    }

    @FXML
    private void flipCard(MouseEvent event) {
        if(currentWord != null) {
            if(showTranslation) {
                wordLabel.setText(currentWord.getForeignWord());
                showTranslation = false;
            } else {
                wordLabel.setText(currentWord.getTranslatedWord());
                showTranslation = true;
            }
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }

    @FXML
    private void nextCard(ActionEvent event) {
        nextWord();
    }

    private void nextWord() {
        if (wordList.getWordCount() == 0) {
            nextCardButton.setDisable(true);
            return;
        }
        if (showTranslation && currentWord != null) {
            wordLabel.setText(currentWord.getTranslatedWord());
            showTranslation = false; 
        } else {
            currentWord = wordList.getNextWord();
            wordLabel.setText(currentWord.getForeignWord());
            showTranslation = true;
        }
    }

}
