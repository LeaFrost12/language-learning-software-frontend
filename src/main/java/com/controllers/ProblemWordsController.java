package com.controllers;

import java.io.IOException;
import java.util.List;

import com.language.App;
import com.model.User;
import com.model.UserList;
import com.model.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ProblemWordsController {

    @FXML
    private ComboBox<String> unitComboBox;

    @FXML
    private GridPane wordGrid;

    @FXML
    private Label wordDetailsLabel;

    private User currentUser;

    /**
     * Initialize the controller.
     */
    public void initialize() {
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();

        if (currentUser != null) {
            loadProblemWords();
        } else {
            showNoUserLoggedInMessage();
        }
    }

    /**
     * Load problem words for the current user into the GridPane.
     */
    private void loadProblemWords() {
        List<Word> problemWords = currentUser.getProblemWordList().getWords();
        wordGrid.getChildren().clear();

        if (problemWords.isEmpty()) {
            Label emptyLabel = new Label("No problem words logged.");
            wordGrid.add(emptyLabel, 0, 0);
        } else {
            for (int i = 0; i < problemWords.size(); i++) {
                Word word = problemWords.get(i);

                Label wordLabel = new Label(word.getForeignWord());
                Label translationLabel = new Label(word.getTranslatedWord());
                Label partOfSpeechLabel = new Label(word.getPartofSpeech());

                // Add labels to the GridPane
                wordGrid.add(wordLabel, 0, i);
                wordGrid.add(translationLabel, 1, i);
                wordGrid.add(partOfSpeechLabel, 2, i);

                // Event to show word details on selection
                wordLabel.setOnMouseClicked(e -> displayWordDetails(word));
            }
        }
    }

    /**
     * Display details for the selected word in the details label.
     */
    private void displayWordDetails(Word word) {
        String details = String.format("Word: %s\nTranslation: %s\nPart of Speech: %s\nExample: %s",
                word.getForeignWord(), word.getTranslatedWord(), word.getPartofSpeech(), word.getExampleSentence());
        wordDetailsLabel.setText(details);
    }

    /**
     * Show a message when no user is logged in.
     */
    @FXML
    private void showNoUserLoggedInMessage() {
        Label message = new Label("No user logged in.");
        wordGrid.add(message, 0, 0);
    }

    /**
     * Handle the Begin Review button click event.
     */
    @FXML
    private void onBeginReviewClicked(ActionEvent event) throws IOException {
        App.setRoot("flashcardPW");
    }

    /**
     * Handle the Back to Home button click event.
     */
    @FXML
    private void back(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}
