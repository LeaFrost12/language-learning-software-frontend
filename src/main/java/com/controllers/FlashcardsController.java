package com.controllers;

import java.io.IOException;

import com.language.App;
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

    @FXML
    private void initialize() {
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();
        wordList = currentUser.getProblemWordList();
        nextWord();
        waveImage.setImage(new Image(getClass().getResourceAsStream("/images/wave.png")));

    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }

    @FXML
    void nextCard(ActionEvent event) {
        nextWord();
    }

    private void nextWord() {
        Word nextWord = wordList.getNextWord();
        wordLabel.setText(nextWord.getForeignWord());
    }

}
