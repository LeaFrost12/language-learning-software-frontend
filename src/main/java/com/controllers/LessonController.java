package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.App;
import com.model.Lesson;
import com.model.Matching;
import com.model.Question;
import com.model.FillInTheBlank;
import com.model.MultipleChoice;
import com.model.WordBank;
import com.model.User;
import com.model.UserList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LessonController {
    @FXML
    private Label lessonTitleLabel;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button nextButton;

    @FXML
    private GridPane matchingGrid;

    @FXML
    private VBox questionContainer;

    @FXML
    private VBox standardQuestionContainer;

    @FXML
    private VBox matchingContainer;

    @FXML
    private Label questionLabel;

    @FXML
    private TextField answerField;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private User currentUser;
    private Lesson currentLesson;
    private Map<String, TextField> matchingInputs;

    public void initialize() {
        UserList userList = UserList.getInstance();
        currentUser = userList.getCurrentUser();

        if (currentUser != null) {
            currentLesson = currentUser.getCurrentLesson();

            if (currentLesson != null) {
                lessonTitleLabel.setText(currentLesson.getLessonName());
                questions = currentLesson.getQuestions();

                if (questions != null && !questions.isEmpty()) {
                    displayQuestion();
                } else {
                    feedbackLabel.setText("No questions available in this lesson.");
                }
            } else {
                lessonTitleLabel.setText("No current lesson found.");
            }
        } else {
            lessonTitleLabel.setText("User not logged in.");
        }
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        resetContainers(); // Ensure all containers are cleared/reset
        feedbackLabel.setText("");
        nextButton.setDisable(true);
    
        if (question instanceof Matching) {
            // Handle Matching Questions
            matchingContainer.setVisible(true);
            matchingContainer.setManaged(true);
            questionContainer.setVisible(false);
            questionContainer.setManaged(false);
            displayMatchingQuestion((Matching) question);
        } else {
            // Handle Standard Questions
            matchingContainer.setVisible(false);
            matchingContainer.setManaged(false);
            questionContainer.setVisible(true);
            questionContainer.setManaged(true);
    
            questionLabel.setText(question.getQuestionText());
    
            if (question instanceof FillInTheBlank) {
                // Display Fill in the Blank
                displayFillInTheBlankQuestion((FillInTheBlank) question);
            } else if (question instanceof MultipleChoice) {
                // Display Multiple Choice
                displayMultipleChoiceQuestion((MultipleChoice) question);
            } else if (question instanceof WordBank) {
                // Display Word Bank
                displayWordBankQuestion((WordBank) question);
            }
        }
    }
    

    private void resetContainers() {
        matchingGrid.getChildren().clear();
        questionContainer.getChildren().clear();
        answerField.clear();
        feedbackLabel.setText("");
        matchingContainer.setVisible(false);
        matchingContainer.setManaged(false);
        standardQuestionContainer.setVisible(false);
        standardQuestionContainer.setManaged(false);
        answerField.setVisible(false);
    }

    private void displayMatchingQuestion(Matching matchingQuestion) {
        matchingGrid.setVisible(true);
        matchingInputs = new HashMap<>();

        List<String> englishWords = matchingQuestion.getEnglishWords();
        for (int i = 0; i < englishWords.size(); i++) {
            Label englishWordLabel = new Label(englishWords.get(i));
            matchingGrid.add(englishWordLabel, 0, i);

            TextField inputField = new TextField();
            matchingGrid.add(inputField, 1, i);
            matchingInputs.put(englishWords.get(i), inputField);
        }
    }

    private void displayFillInTheBlankQuestion(FillInTheBlank question) {
        // Clear existing content in questionContainer
        questionContainer.getChildren().clear();
    
        // Add question text
        Label questionTextLabel = new Label(question.getQuestionText());
        questionContainer.getChildren().add(questionTextLabel);
    
        // Add answer field dynamically
        answerField = new TextField();
        answerField.setPromptText("Type your answer here.");
        questionContainer.getChildren().add(answerField);
    
        // Ensure container is visible
        questionContainer.setVisible(true);
        questionContainer.setManaged(true);
    }
    

    private void displayMultipleChoiceQuestion(MultipleChoice question) {
        questionLabel.setText(question.getQuestionText());
        for (String choice : question.getChoices()) {
            Button choiceButton = new Button(choice);
            choiceButton.setOnAction(e -> handleMultipleChoiceSelection(question, choice));
            questionContainer.getChildren().add(choiceButton);
        }
    }

    private void handleMultipleChoiceSelection(MultipleChoice question, String selectedChoice) {
        if (question.checkAnswer(selectedChoice)) {
            feedbackLabel.setText("Correct!");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + question.getCorrectAnswer());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
        nextButton.setDisable(false);
    }

    private void displayWordBankQuestion(WordBank question) {
        questionLabel.setText(question.getQuestionText());
        for (String word : question.getWordBankText()) {
            Button wordButton = new Button(word);
            wordButton.setOnAction(e -> handleWordBankSelection(question, word));
            questionContainer.getChildren().add(wordButton);
        }
    }

    private void handleWordBankSelection(WordBank question, String selectedWord) {
        if (question.checkAnswer(selectedWord)) {
            feedbackLabel.setText("Correct!");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + question.getCorrectAnswer());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
        nextButton.setDisable(false);
    }

    @FXML
    private void onSubmitClicked() {
        Question question = questions.get(currentQuestionIndex);

        if (question instanceof Matching) {
            validateMatchingQuestion((Matching) question);
        } else if (question instanceof FillInTheBlank) {
            String userAnswer = answerField.getText().trim();
            if (userAnswer.isEmpty()) {
                feedbackLabel.setText("Please enter an answer.");
                feedbackLabel.setStyle("-fx-text-fill: orange;");
                return;
            }

            if (question.checkAnswer(userAnswer)) {
                feedbackLabel.setText("Correct!");
                feedbackLabel.setStyle("-fx-text-fill: green;");
            } else {
                feedbackLabel.setText("Incorrect. The correct answer is: " + question.getCorrectAnswer());
                feedbackLabel.setStyle("-fx-text-fill: red;");
            }
        }

        nextButton.setDisable(false);
    }

    private void validateMatchingQuestion(Matching matchingQuestion) {
        boolean allCorrect = true;
        StringBuilder feedback = new StringBuilder();

        Map<String, String> correctAnswers = matchingQuestion.getAnswers();
        for (Map.Entry<String, TextField> entry : matchingInputs.entrySet()) {
            String englishWord = entry.getKey();
            TextField inputField = entry.getValue();
            String userAnswer = inputField.getText().trim();

            if (correctAnswers.get(englishWord).equalsIgnoreCase(userAnswer)) {
                inputField.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            } else {
                inputField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                allCorrect = false;
                feedback.append(String.format("Correct answer for '%s' is '%s'.\n",
                        englishWord, correctAnswers.get(englishWord)));
            }
        }

        if (allCorrect) {
            feedbackLabel.setText("All answers are correct! Well done.");
            feedbackLabel.setStyle("-fx-text-fill: green;");
        } else {
            feedbackLabel.setText(feedback.toString());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void onNextQuestionClicked() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            feedbackLabel.setText("You have completed the lesson!");
            nextButton.setDisable(true);
        }
    }

    @FXML
    private void onBackHomeClicked(ActionEvent event) throws IOException {
        App.setRoot("user_home");
    }
}