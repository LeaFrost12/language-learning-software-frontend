package com.controllers;

import com.language.App;
import com.model.User;
import com.model.UserList;
import com.model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class ProblemWordsController {

    @FXML
    private ListView<String> problemWordsList;

    @FXML
    private Label wordDetailsLabel;

    @FXML
    private Button backHomeButton;

    private User currentUser;

    public void initialize() {
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();

        if (currentUser != null) {
            loadProblemWords();
        } else {
            problemWordsList.getItems().add("No user logged in.");
        }
    }

    private void loadProblemWords() {
        List<Word> problemWords = currentUser.getProblemWordList().getWords();

        problemWordsList.getItems().clear(); // Clear existing items

        if (problemWords.isEmpty()) {
            problemWordsList.getItems().add("No problem words logged.");
        } else {
            for (Word word : problemWords) {
                problemWordsList.getItems().add(word.getForeignWord());
            }

            problemWordsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Word selectedWord = problemWords.stream()
                        .filter(word -> word.getForeignWord().equals(newValue))
                        .findFirst()
                        .orElse(null);

                if (selectedWord != null) {
                    displayWordDetails(selectedWord);
                }
            });
        }
    }

    private void displayWordDetails(Word word) {
        String details = String.format("Word: %s\nTranslation: %s\nPart of Speech: %s\nExample: %s",
                word.getForeignWord(), word.getTranslatedWord(), word.getPartofSpeech(), word.getExampleSentence());
        wordDetailsLabel.setText(details);
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
